(ns report-gen.dispatch
  (:gen-class)
  (:require [report-gen.helpers :as helpers :refer [gen-file-name]]
            [clojure.tools.logging :as log]
            [postal.core :as postal]))

(defn dispatch-email [email-props report-props email-server & [report-dir]]
  (log/info "dispatching email" email-props)
  (let [report-name       (:name report-props)
        report-file-name  (str report-dir "/" (helpers/gen-file-name report-name (:output report-props)))
        _                 (log/info "email server:" email-server)
        _                 (log/info "attachment file name:" report-file-name)
        from              (:from email-props)
        to                (:to email-props)
        cc                (:cc email-props)
        bcc               (:bcc email-props)
        subject           (:subject email-props)
        body              [{:type "text/html; charset=utf-8"
                            :content (:body email-props)}
                           {:type :attachment
                            :content (java.io.File. report-file-name)}]
        email-msg         {:from from :to to :cc cc :bcc bcc :subject subject :body body}
        postal-response   (if (nil? (:host email-server)) ;; nil email-server use sendmail compatible mta on server
                            (postal/send-message email-msg)
                            (postal/send-message email-server email-msg))]
    (log/info postal-response)))

(defn route-dispatch [dispatch-props report-props email-server & [report-dir]]
  (log/info "dispatch-route " dispatch-props)
  (let [dispatch-type (:type dispatch-props)]
    (case dispatch-type
      :email (dispatch-email dispatch-props report-props email-server report-dir)
      (log/error "Invalid dispatch type"))))

(defn dispatch-report [dispatch-props-vec report-props email-server & [report-dir]]
  (log/info "dispatch " dispatch-props-vec)
  ;; iterate over all dispatch values
  (log/info (map #(route-dispatch % report-props email-server report-dir) dispatch-props-vec)))
