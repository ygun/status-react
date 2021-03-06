(ns status-im.ui.screens.discover.styles
  (:require-macros [status-im.utils.styles :refer [defnstyle]])
  (:require [status-im.components.styles :as styles]
            [status-im.components.toolbar.styles :refer [toolbar-background2]]
            [status-im.components.tabs.styles :as tabs-st]
            [status-im.utils.platform :as p]))

;; Common

(def row-separator
  {:border-bottom-width 1
   :border-bottom-color "#eff2f3"})

(def row
  {:flex-direction :row
   :margin-bottom  10})

(def column
  {:flex-direction :column})

(def empty-view
  {:flex             1
   :background-color styles/color-white
   :align-items      :center
   :justify-content  :center})

(def title
  {:padding         16
   :flex-direction  :row
   :justify-content :space-between})

;; Popular

(def popular-container
  {:background-color toolbar-background2})

(def carousel-page-style
  {})

(def tag-button
  {:color           styles/color-blue
   :font-size       14
   :padding-right   5
   :padding-bottom  2
   :align-items     :center
   :justify-content :center})

(def tag-name
  {:color            styles/color-blue
   :background-color :white
   :font-size        14
   :align-items      :center
   :justify-content  :center})

(def tag-count
  {:color           "#838c93"
   :font-size       12
   :padding-right   6
   :padding-bottom  2
   :align-items     :center
   :justify-content :center})

(def tag-count-container
  {:flex           0.2
   :flex-direction "column"
   :align-items    "flex-end"
   :padding-top    6
   :padding-right  9})

(def separator
  {:background-color "rgb(238, 241, 245)"
   :height           2
   :margin-top       2
   :margin-bottom    2})

;; Popular list item

(def popular-list-container
  {:flex             1
   :background-color :white
   :padding-top      18
   :padding-left     16
   })

(def popular-list-item
  {:flex-direction "column"
   :padding-bottom 16
   :margin-right   10
   :top            1})

(def popular-list-item-second-row
  {:flex            1
   :flex-direction  :row
   :align-items     :center
   :justify-content :space-between
   :margin-bottom   5
   :padding-top     25})

(def popular-list-item-name-container
  {:flex            0.3
   :flex-direction  :row
   :justify-content :flex-start})

(def popular-list-item-name
  {:margin-left 7
   :color       "black"
   :font-size   12})

(def popular-list-item-avatar-container
  {:flex-direction "column"})

(def popular-list-chat-action
  {:background-color "rgb(220, 214, 251)"
   :flex-direction   :row
   :border-radius    5
   :padding          4})

(def popular-list-chat-action-text
  {:color "rgb(110, 0, 228)"})

;; discover_recent

(def recent-container
  {:background-color toolbar-background2})

(def recent-list
  {:background-color :white
   :padding-left     16})

;; Discover tag

(def discover-tag-toolbar
  {:border-bottom-color "#D7D7D7"
   :border-bottom-width 1})

(def discover-tag-container
  {:flex            1
   :backgroundColor styles/color-light-gray})

(def tag-title-scroll
  {:flex           1
   :alignItems     "center"
   :justifyContent "center"})

(def tag-title-container
  {:flex           0.2
   :alignItems     "center"
   :justifyContent "center"
   :flex-direction "row"})

(def tag-title
  {:color          styles/color-blue
   :font-size      14
   :padding-right  5
   :padding-bottom 2})

(def icon-back
  {:width  8
   :height 14})

(def icon-search
  {:width  17
   :height 17})

(def discover-container
  {:flex            1
   :backgroundColor styles/color-white})

(def list-container
  {:flex 1})

(def search-icon
  {:width  17
   :height 17})

(defn title-action-text  [active?]
  {:color (if active?
            styles/color-blue
            styles/color-gray-transparent)})

(def recent-statuses-preview-container
  {:background-color toolbar-background2})

(def recent-statuses-preview-content
  {:border-radius    4
   :padding-top      18
   :padding-left     16
   :margin-top       2
   :margin-bottom    4
   :margin-right     2
   :background-color :white})

(def public-chats-item-container
  {:flex-direction :row
   :padding        3})

(def public-chats-icon-width-ratio
  0.15)

(def public-chats-icon-container
  {:flex public-chats-icon-width-ratio})

(defn public-chats-icon [color]
  {:width            50
   :height           50
   :border-radius    25
   :background-color color
   :flex-direction   :row
   :justify-content  :center
   :align-items      :center})

(def public-chats-icon-text
  {:font-size 25
   :color     styles/color-gray-transparent-light})

(def public-chats-item-inner
  {:flex           (- 1 public-chats-icon-width-ratio)
   :margin-left    10
   :flex-direction :column})

(def public-chats-item-name-container
  {:flex-direction :row})

(def public-chats-item-name-text
  {:color       styles/color-gray-transparent
   :font-weight :bold
   :font-size   16
   :margin-left 5})