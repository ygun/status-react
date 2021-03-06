(ns status-im.ui.screens.wallet.events
  (:require [re-frame.core :as re-frame :refer [dispatch reg-fx]]
            [status-im.utils.handlers :as handlers]
            [status-im.utils.prices :as prices]
            [status-im.utils.transactions :as transactions]
            [status-im.utils.utils :as utils]
            [status-im.ui.screens.wallet.db :as wallet.db]

            [status-im.native-module.core :as status]
            [status-im.ui.screens.wallet.navigation]
            [taoensso.timbre :as log]
            status-im.ui.screens.wallet.request.events))

(defn get-balance [{:keys [web3 account-id on-success on-error]}]
  (if (and web3 account-id)
    (.getBalance
     (.-eth web3)
     account-id
     (fn [err resp]
       (if-not err
         (on-success resp)
         (on-error err))))
    (on-error "web3 or account-id not available")))

(defn assoc-error-message [db error-type err]
  (assoc-in db [:wallet :errors error-type] (or (when err (str err))
                                                :unknown-error)))

(defn clear-error-message [db error-type]
  (update-in db [:wallet :errors] dissoc error-type))

;; FX

(reg-fx
  :get-balance
  (fn [{:keys [web3 account-id success-event error-event]}]
    (get-balance {:web3           web3
                  :account-id     account-id
                  :on-success     #(dispatch [success-event %])
                  :on-error       #(dispatch [error-event %])})))

(reg-fx
  :get-transactions
  (fn [{:keys [network account-id success-event error-event]}]
    (transactions/get-transactions network
                                   account-id
                                   #(dispatch [success-event %])
                                   #(dispatch [error-event %]))))

;; TODO(oskarth): At some point we want to get list of relevant assets to get prices for
(reg-fx
  :get-prices
  (fn [{:keys [from to success-event error-event]}]
    (prices/get-prices from
                       to
                       #(dispatch [success-event %])
                       #(dispatch [error-event %]))))

;; Handlers

(handlers/register-handler-fx
  :update-wallet
  (fn [{{:keys [web3 accounts/current-account-id network] :as db} :db} [_ a]]
    {:get-balance {:web3          web3
                   :account-id    current-account-id
                   :success-event :update-balance-success
                   :error-event   :update-balance-fail}
     :get-prices  {:from          "ETH"
                   :to            "USD"
                   :success-event :update-prices-success
                   :error-event   :update-prices-fail}
     :db          (-> db
                      (clear-error-message :prices-update)
                      (clear-error-message :balance-update)
                      (assoc-in [:wallet :balance-loading?] true)
                      (assoc :prices-loading? true))}))

(handlers/register-handler-fx
  :update-transactions
  (fn [{{:keys [accounts/current-account-id network] :as db} :db} _]
    {:get-transactions {:account-id    current-account-id
                        :network       network
                        :success-event :update-transactions-success
                        :error-event   :update-transactions-fail}
     :db               (-> db
                           (clear-error-message :transaction-update)
                           (assoc-in [:wallet :transactions-loading?] true))}))

(handlers/register-handler-db
  :update-transactions-success
  (fn [db [_ transactions]]
    (-> db
        (assoc-in [:wallet :transactions] transactions)
        (assoc-in [:wallet :transactions-loading?] false))))

(handlers/register-handler-db
  :update-transactions-fail
  (fn [db [_ err]]
    (log/debug "Unable to get transactions: " err)
    (-> db
        (assoc-error-message :transactions-update err)
        (assoc-in [:wallet :transactions-loading?] false))))

(handlers/register-handler-db
  :update-balance-success
  (fn [db [_ balance]]
    (-> db
        (assoc-in [:wallet :balance] balance)
        (assoc-in [:wallet :balance-loading?] false))))

(handlers/register-handler-db
  :update-balance-fail
  (fn [db [_ err]]
    (log/debug "Unable to get balance: " err)
    (-> db
        (assoc-error-message :balance-update err)
        (assoc-in [:wallet :balance-loading?] false))))

(handlers/register-handler-db
  :update-prices-success
  (fn [db [_ prices]]
    (assoc db
           :prices prices
           :prices-loading? false)))

(handlers/register-handler-db
  :update-prices-fail
  (fn [db [_ err]]
    (log/debug "Unable to get prices: " err)
    (-> db
        (assoc-error-message :prices-update err)
        (assoc :prices-loading? false))))

(handlers/register-handler-fx
  :show-transaction-details
  (fn [{:keys [db]} [_ hash]]
    {:db (assoc-in db [:wallet :current-transaction] hash)
     :dispatch [:navigate-to :wallet-transaction-details]}))
