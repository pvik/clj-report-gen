(ns report-gen.core
  (:gen-class)
  (:require [propertea.core :refer (read-properties)]
            [clojure.tools.logging :as log]
            [clojure.tools.cli :refer [cli]]
            [clojure.java.jdbc :as jdbc]))

(defn -main
  "report-gen application entry point"
  [& args]
  (let [[opts args banner] (cli args
                                ["-h" "--help" "Print this help"
                                 :default false :flag true]
                                ["-d" "--db-props"  "Database properties file"
                                 :default "resources/db.properties"]
                                ["-r" "--report" "Report .edn file"])]
    (when (:help opts)
      (println banner)
      (System/exit 0))
    (if (nil? (:report opts))
      (let []
        (println "ERROR: No Report file specified")
        (log/error "No Report file specified")
        (println banner)
        (System/exit 0))
      (let [report (read-string (slurp (:report opts)))
            db-props (read-properties (:db-props opts))]
        (log/logf :info "Running report: %s" (:name report))
        (log/info report)
        (log/info db-props)))))

(defn run-query [db-props sql]
  (let [db {:dbtype }]))
