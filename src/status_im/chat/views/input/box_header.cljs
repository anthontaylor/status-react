(ns status-im.chat.views.input.box-header
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require [re-frame.core :refer [subscribe dispatch]]
            [status-im.components.react :refer [view
                                                touchable-highlight
                                                text
                                                icon]]
            [status-im.chat.styles.input.box-header :as style]
            [taoensso.timbre :as log]))

(defn get-header [type]
  (fn []
    (let [parameter-box (subscribe [:chat-parameter-box])
          result-box    (subscribe [:chat-ui-props :result-box])]
      (fn []
        (let [{:keys [showBack title]} (if (= type :parameter-box)
                                         @parameter-box
                                         @result-box)]
          [view {:style style/header-container}
           [view style/header-title-container
            (when showBack
              [touchable-highlight {:on-press #(dispatch [:select-argument 2])}
               [view style/header-back-container
                [icon :back_dark style/header-close-icon]]])
            [text {:style           (style/header-title-text showBack)
                   :number-of-lines 1
                   :font            :medium}
             title]
            [touchable-highlight {:on-press #(dispatch [:set-chat-ui-props {:result-box nil}])}
             [view style/header-close-container
              [icon :close_light_gray style/header-close-icon]]]]])))))