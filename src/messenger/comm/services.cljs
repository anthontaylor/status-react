(ns messenger.comm.services
  (:require
    [syng-im.utils.logging :as log]
    [messenger.services.user-data :refer [user-data-handler]]))

(defmulti service (fn [state service-id args]
                    service-id))

(defmethod service :user-data
  [state service-id args]
  (user-data-handler state args))

(defn services-handler [state service-id args]
  (log/info "handling " service-id " args = " args)
  (service state service-id args))