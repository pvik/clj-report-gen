(ns report-gen.dispatch
  (:gen-class)
  (:require [report-gen.helpers :as helpers :refer [gen-file-name]]
            [clojure.tools.logging :as log]
            [postal.core :as postal]))

(defn dispatch-email [email-props report-props email-server & [report-dir]]
  (log/info "dispatching email" email-props)
  (let [report-name (:name report-props)
        report-file-name  (str report-dir "/" (helpers/gen-file-name report-name (:output report-props)))
        from (:from email-props)
        to (:to email-props)
        subject (:subject email-props)
        body [{:type "text/html; charset=utf-8"
               :content (:body email-props)}
              {:type :attachment
               :content (java.io.File. report-file-name)}]
        email-msg {:from from :to to :subject subject :body body}]
    (postal/send-message email-msg)))

(defn route-dispatch [dispatch-props report-props email-server & [report-dir]]
  (let [dispatch-type (:type dispatch-props)]
    (case dispatch-type
      :email (dispatch-email dispatch-props report-props email-server report-dir)
      (log/error "Invalid dispatch type"))))

(defn dispatch-report [dispatch-props-vec report-props email-server & [report-dir]]
  ;; iterate over all dispatch values
  (map #(route-dispatch % report-props email-server report-dir) dispatch-props-vec))
