(ns clj-libs.zip-seqs
  (:gen-class)
  (:use clj-libs.private.simple-andmap))

(defn zip
  "Merge several sequences"
  [ss]
  {:pre [(sequential? ss) (simple-andmap sequential? ss)]}
  (lazy-seq
   (letfn [(loop
             [ss]
             (if (simple-andmap seq ss)
               (cons (map first ss) (loop (map rest ss)))
               '()))]
     (loop ss))))
