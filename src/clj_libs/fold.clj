(ns clj-libs.fold
  (:use clj-libs.private.simple-foldr
        clj-libs.map-utils
        clj-libs.zip-seqs))

(defn foldr
  "The fold operator"
  ([p i s]
   {:pre [(fn? p) (coll? s)]}
   (simple-foldr p i s))
  ([p i s0 s1 & ss]
   {:pre [(fn? p) (andmap coll? (cons s0 (cons s1 ss)))]}
   (foldr (fn [l o] (apply p `(~@l ~o))) i (zip (cons s0 (cons s1 ss))))))
(defn foldl
  "The foldl operator, which is like the reduce function except that it supports an arbitrary number of collections"
  ([p i s]
   {:pre [(fn? p) (coll? s)]}
   (reduce p i s))
  ([p i s0 s1 & ss]
   {:pre [(fn? p) (andmap coll? (cons s0 (cons s1 ss)))]}
   (foldl (fn [o l] (apply p o l)) i (zip (cons s0 (cons s1 ss))))))
