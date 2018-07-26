(ns report-gen.core
  (:gen-class)
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.tools.logging :as log]
            [report-gen.dispatch :as dispatch]
            [report-gen.helpers :as helpers]
            [report-gen.output :as output]
            [report-gen.sql :as sql]))

(defn -main
  "report-gen application entry point"
  [& args]
  (let [[opts args banner]
        (cli args
             ["-h" "--help"
              "Print this help"
              :default false :flag true]
             ["-d" "--data-dir"
              "data directory to find report and db .edn files"
              :default "resources"]
             ["-o" "--output-dir"
              "directory to store generated reports"
              :default "/tmp"]
             ["-e" "--email-server"
              "email server properties"
              :default "resources/email.edn"]
             ["-r" "--report"
              "Report .edn file"])]
    (when (:help opts)
      (println banner)
      (System/exit 0))
    (if (nil? (:report opts))
      (let []
        (println "ERROR: No Report file specified")
        (log/error "No Report file specified  df")
        (println banner)
        (System/exit 0))
      (let [report             (:report opts)
            data-dir           (:data-dir opts)
            output-dir         (:output-dir opts)
            email-server-props (helpers/read-edn (:email-server opts))
            report-props       (helpers/read-report report data-dir)
            output-props       (:output report-props)
            columns            (:columns report-props)
            dispatch-props-vec (:dispatch report-props)
            report-name        (:name report-props)
            ;; run report
            report-data        (sql/run-report report-props data-dir)
            report-count       (count report-data)]
        (log/info "Report Data: " report-data)
        (if (> report-data 0)
          (do
            ;; generate output files
            (output/gen-output report-name
                               report-data
                               output-props
                               columns
                               output-dir)
            ;; dispatch report
            (dispatch/dispatch-report dispatch-props-vec
                                      report-props
                                      email-server-props
                                      output-dir))
          (log/info "No records found"))
        (log/info "Done!")))))


