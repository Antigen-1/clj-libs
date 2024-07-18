(ns clj-libs.fold
  (:use clj-libs.private.simple-foldr
        clj-libs.map-utils
        clj-libs.zip-seqs))

(defn foldr
  "The fold operator"
  ([p i s]
   {:pre [(coll? s)]}
   (simple-foldr p i s))
  ([p i s0 s1 & ss]
   {:pre [(andmap coll? (conj ss s1 s0))]}
   (foldr (fn [o l] (apply p o l)) i (zip (conj ss s1 s0)))))
(defn foldl
  "The foldl operator, which is like the reduce function except that it supports an arbitrary number of collections"
  ([p i s]
   {:pre [(coll? s)]}
   (reduce p i s))
  ([p i s0 s1 & ss]
   {:pre [(andmap coll? (conj ss s1 s0))]}
   (foldl (fn [o l] (apply p o l)) i (zip (conj ss s1 s0)))))
