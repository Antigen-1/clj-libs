(ns clj-libs.andmap
  (:gen-class)
  (:use clj-libs.zip-seqs
        clj-libs.private.simple-andmap))

(defn andmap
  "Like map except that it returns false or nil when applying the procedure to an element in the sequence returns false or nil"
  ([p s]
   {:pre [(sequential? s)]}
   (simple-andmap p s))
  ([p s0 s1 & ss]
   {:pre [(andmap sequential? (conj ss s1 s0))]}
   (andmap (fn [xs] (apply p xs)) (zip (conj ss s1 s0)))))
