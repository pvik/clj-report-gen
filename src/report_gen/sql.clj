(ns report-gen.sql
  (:gen-class)
  (:require [report-gen.helpers :as helpers :refer [read-db]]
            [clojure.tools.logging :as log]
            [clojure.java.jdbc :as jdbc]))

(defn run-report [report-props data-dir]
  (log/logf :info "Running report: %s" (:name report-props))
  (let [db-props (read-db (:db report-props) data-dir)]
    (log/info db-props))
  (+ 1 1))
