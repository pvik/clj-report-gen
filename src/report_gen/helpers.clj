(ns report-gen.helpers
  (:gen-class)
  (:require [clojure.tools.logging :as log]))

(defn read-edn [edn-file]
  (read-string (slurp edn-file)))

(defn read-db [db-name & dir]
  (let [db-props-file (str (if (nil? dir) "resources" dir) "/databases/" db-name ".edn")]
    (log/info "reading db file" db-props-file)
    (read-edn db-props-file)))

(defn read-report [report-name & dir]
  (let [report-props-file (str (if (nil? dir) "resources" dir) "/reports/" report-name ".edn")]
    (log/info "reading report file" report-props-file)
    (read-edn report-props-file)))
