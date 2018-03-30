(ns report-gen.output
  (:gen-class)
  (:require [report-gen.helpers :as helpers :refer [seq-map-to-2d-vec gen-file-name]]
            [dk.ative.docjure.spreadsheet :as docjure]
            [clojure.tools.logging :as log]))

(defn save-xlsx [report-name report-data & report-dir]
  (let [filename (helpers/gen-file-name report-name :xlsx)
        wb (docjure/create-workbook report-name (helpers/seq-map-to-2d-vec report-data))]
    (log/info "saving " filename)
    (docjure/save-workbook! filename wb)))
