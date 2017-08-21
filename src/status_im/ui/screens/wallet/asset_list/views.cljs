(ns status-im.ui.screens.wallet.asset-list.views
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require [clojure.string :as string]
            [re-frame.core :as rf]
            [status-im.components.context-menu :as [context-menu]]
            [status-im.components.react :as rn]
            [status-im.components.status-bar :as status-bar]
            [status-im.components.toolbar-new.view :as toolbar]
            [status-im.components.toolbar-new.actions :as act]
            [status-im.i18n :as i18n]
            [status-im.ui.screens.wallet.asset-list.styles :as st]))

(defn toolbar-buttons []
  [rn/view {:style st/toolbar-buttons-container}
   [rn/icon :add_dark st/toolbar-icon]])

(defn toolbar-view [transactions]
  [toolbar/toolbar {:style st/toolbar
                    :title "Assets"
                    :nav-action (act/close #(rf/dispatch [:navigate-back]))
                    :custom-action [toolbar-buttons]}])

;; (defn options-btn [chat-id]
;;   (let [options [{:value        #(dispatch [:remove-chat chat-id])
;;                   :text         (label :t/delete-chat)
;;                   :destructive? true}]]
;;     [view st/opts-btn-container
;;      [context-menu
;;       [icon :options_gray]
;;       options
;;       nil
;;       st/opts-btn]]))

(defn asset-iconsn [hidden?]
  [rn/view {:style st/asset-icons-container}
   [rn/touchable-highlight {:on-press #()
                           :style (st/asset-toggle-button hidden?)}]])

;; TODO: Add icon to each asset
(defn asset-list-item [{:keys [name symbol hidden?] :as row}]
  [rn/view {:style st/asset-container}
   [rn/image {:source {:uri :launch_logo}
              :style  st/asset-icon}]
   [rn/view {:style st/asset-info-container}
    [rn/text {:style st/asset-name} name]
    [rn/text {:style st/asset-symbol} symbol]]
   [asset-icons hidden?]])

(defn asset-list []
  (let [assets [{:name    "Ethereum"
                 :symbol  "ETH"
                 :hidden? true}
                {:name    "Status Network"
                 :symbol  "SNT"
                 :hidden? false}
                {:name    "Golem"
                 :symbol  "GLM"
                 :hidden? false}

                ]]
    [rn/flat-list assets asset-list-item {:style st/asset-list}]))

(defview asset-list-screen []
  []
  [rn/view {:style st/screen-container}
   [status-bar/status-bar {:type :main}]
   [toolbar-view]
   [asset-list]])
