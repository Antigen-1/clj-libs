(ns clj-libs.zip-seqs
  (:gen-class)
  (:use clj-libs.private.simple-map-utils))

(defn zip
  "Merge several sequences"
  [ss]
  {:pre [(sequential? ss) (simple-andmap sequential? ss)]}
  (letfn [(loop
            [ss]
            (lazy-seq
             (if (simple-andmap seq ss)
               (cons (map first ss) (loop (map rest ss)))
               '())))]
    (loop ss)))
