(ns report-gen.output
  (:gen-class)
  (:use [dk.ative.docjure.spreadsheet])
  (:require [report-gen.helpers :as helpers :refer [seq-map-to-2d-vec gen-file-name]]
            [clojure.tools.logging :as log]))

(defn save-xlsx [report-name report-data & report-dir]
  (let [filename (helpers/gen-file-name report-name :xlsx)
        wb (create-workbook report-name (helpers/seq-map-to-2d-vec report-data))]
    (log/info "saving " filename)
    (save-workbook! filename wb)))
