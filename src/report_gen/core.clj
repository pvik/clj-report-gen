(ns report-gen.core
  (:gen-class)
  (:require [report-gen.helpers :as helpers :refer [read-report]]
            [report-gen.sql :as sql :refer [run-report]]
            [clojure.tools.logging :as log]
            [clojure.tools.cli :refer [cli]]))

(defn -main
  "report-gen application entry point"
  [& args]
  (let [[opts args banner] (cli args
                                ["-h" "--help" "Print this help"
                                 :default false :flag true]
                                ["-d" "--data-dir"  "data directory to find report and db .edn files"
                                 :default "resources"]
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
      (let [report-name (:report opts)
            report-props (helpers/read-report report-name)
            report-data (sql/run-report report-props)
            db-props "props"]
        (log/info db-props)))))
