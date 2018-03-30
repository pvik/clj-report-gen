(ns report-gen.dispatch
  (:gen-class)
  (:require [report-gen.helpers :as helpers :refer [gen-file-name]]
            [clojure.tools.logging :as log]))

(defn dispatch-email [dispatch-props output-props]
  (log/info "dispatching email"))

(defn route-dispatch [dispatch-props output-props]
  (let [dispatch-type (:type dispatch-props)]
    (case dispatch-type
      :email (dispatch-email dispatch-props output-props))))
