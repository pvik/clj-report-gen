(ns report-gen.output
  (:gen-class)
  (:require [report-gen.helpers :as helpers :refer [date-time-formatter seq-map-to-2d-vec]]
            [clojure.tools.logging :as log]))

(defn save-xlsx [data-2d-vec report-name & report-dir]
  (let [wb (create-workbook report-name data-2d-vec)]
    (save-workbook! (str report-name "_" (.format (java.time.LocalDateTime/now) helpers/date-time-formatter) ".xlsx")  wb)))
