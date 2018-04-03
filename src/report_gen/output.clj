(ns report-gen.output
  (:gen-class)
  (:use [dk.ative.docjure.spreadsheet])
  (:require [report-gen.helpers :as helpers :refer [seq-map-to-2d-vec gen-file-name]]
            [clojure.tools.logging :as log]))

(defn save-xlsx [report-name report-data & [report-dir]]
  (let [filename (str report-dir "/" (helpers/gen-file-name report-name :xlsx))
        data-2d-vec (helpers/seq-map-to-2d-vec report-data)
        wb (create-workbook report-name data-2d-vec)]
    (log/info "Saving " filename)
    (save-workbook! filename wb)))

(defn gen-output [report-name report-data output-props & [columns output-dir]]
  (log/info "Generating output...")
  (case output-props
    :xlsx (save-xlsx report-name report-data columns output-dir)
    (log/error "INVALID OUTPUT TYPE")))
