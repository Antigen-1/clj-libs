(ns clj-libs.zip-seqs
  (:gen-class)
  (:use clj-libs.private.simple-map-utils))

(defn zip
  "Merge several sequences"
  [[s0 & ss]]
  {:pre [(simple-andmap coll? ss)]}
  (if (seq ss)
    (apply map list s0 ss)
    (map list s0)))
