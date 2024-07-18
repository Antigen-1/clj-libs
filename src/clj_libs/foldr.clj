(ns clj-libs.foldr
  (:use clj-libs.private.simple-foldr
        clj-libs.map-utils
        clj-libs.zip-seqs))

(defn foldr
  "The standard fold operator"
  ([p i s]
   {:pre (sequential? s)}
   (simple-foldr p i s))
  ([p i s0 s1 & ss]
   {:pre (andmap sequential? (conj ss s1 s0))}
   (foldr (fn [o l] (apply p o l)) i (zip (conj ss s1 s0)))))
