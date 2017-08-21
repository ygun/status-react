(ns status-im.ui.screens.wallet.asset-list.styles
  (:require-macros [status-im.utils.styles :refer [defnstyle defstyle]])
  (:require [status-im.components.styles :as common]
            [status-im.utils.platform :as platform]))

(def screen-container
  {:flex             1
   :background-color common/color-white})

(def toolbar
  {:elevation           0
   :border-bottom-width 1
   :border-color        common/color-light-gray2})

(def toolbar-icon
  {:width  24
   :height 24})

(def toolbar-buttons-container
  {:flex-direction :row
   :flex-shrink    1
   :margin-right   8})

;;;;;;;;;;;;;;;;;
;; Asset list ;;
;;;;;;;;;;;;;;;;;

(defstyle asset-list
  {:padding-bottom 16
   :android        {:margin-top 8}})

;;;;;;;;;;;;;;;;;;;;;
;; Asset list item ;;
;;;;;;;;;;;;;;;;;;;;;

(def asset-container
  {:flex-direction :row
   :align-items    :center
   :padding        12})

(def asset-icon
  {:height       40
   :width        40
   :margin-right 14})

(def asset-info-container
  {:flex           1})

(def asset-name
  {:font-size 17
   :color     common/color-black})

(def asset-symbol
  {:font-size   14
   :color       common/color-gray4
   :margin-top 3})

(def asset-icons-container
  {:flex 1
   :flex-shrink 1})

(def asset-list-separator
  {:margin-left         70
   :border-bottom-width 1
   :border-color        common/color-separator})
