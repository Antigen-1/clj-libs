(ns clj-libs.libs.map-utils
  (:use clj-libs.libs.zip-seqs
        clj-libs.private.simple-map-utils))

(defn andmap
  "The procedural version of and"
  ([p s]
   {:pre [(fn? p) (coll? s)]}
   (simple-andmap p s))
  ([p s0 s1 & ss]
   {:pre [(fn? p) (andmap coll? (cons s0 (cons s1 ss)))]}
   (andmap (fn [xs] (apply p xs)) (zip (cons s0 (cons s1 ss))))))
(defn ormap
  "The procedural version of or"
  ([p s]
   {:pre [(fn? p) (coll? s)]}
   (simple-ormap p s))
  ([p s0 s1 & ss]
   {:pre [(fn? p) (andmap coll? (cons s0 (cons s1 ss)))]}
   (ormap (fn [xs] (apply p xs)) (zip (cons s0 (cons s1 ss))))))
