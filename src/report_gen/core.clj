(ns report-gen.core
  (:gen-class)
  (:require [report-gen.helpers :as helpers :refer [read-report]]
            [report-gen.sql :as sql :refer [run-report]]
            [report-gen.output :as output :refer [save-xlsx]]
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
                                ["-o" "--output-dir" "directory to store generated reports"
                                 :default "/tmp"]
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
            data-dir (:data-dir opts)
            output-dir (:output-dir opts)
            report-props (helpers/read-report report-name data-dir)
            report-name (:name report-props)
            report-data (sql/run-report report-props data-dir)]
        (log/info "Report Data: " report-data)
        (case (:output report-props)
          :xlsx (output/save-xlsx report-name report-data output-dir)
          (log/error "INVALID OUTPUT TYPE"))
        (log/info "Done!")))))
