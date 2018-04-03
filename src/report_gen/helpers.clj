(ns report-gen.helpers
  (:gen-class)
  (:require [clojure.tools.logging :as log]))

(def date-time-formatter (java.time.format.DateTimeFormatter/ofPattern "yyyy_MM_dd_HHmm"))

(defn gen-file-name [report-name type]
  (str report-name "_" (.format (java.time.LocalDateTime/now) date-time-formatter) "." (name type)))

(defn read-edn [edn-file]
  (read-string (slurp edn-file)))

(defn read-db [db-name & extr-params]
  (let [dir (first extr-params)
        db-props-file (str dir "/databases/" db-name ".edn")]
    (log/info "reading db file" db-props-file)
    (read-edn db-props-file)))

(defn read-report [report-name & extr-params]
  (let [dir (first extr-params)
        report-props-file (str dir "/reports/" report-name ".edn")]
    (log/info "reading report file" report-props-file)
    (read-edn report-props-file)))

(defn map-vals-to-vec-ord-by-keys [m ks]
  (reduce #(conj % (str (%2 m))) [] ks))

(defn seq-map-to-2d-vec [data-seq & [cols]]
  (let [ks (if (nil? cols) (vec (sort (keys (first data-seq)))) cols)
        ret-data [(reduce #(conj % (name %2)) [] ks)]]
    (reduce #(conj % (map-vals-to-vec-ord-by-keys %2 ks)) ret-data data-seq)))
