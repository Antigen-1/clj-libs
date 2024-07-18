(ns clj-libs.map-utils
  (:gen-class)
  (:use clj-libs.zip-seqs
        clj-libs.private.simple-map-utils))

(defn andmap
  "The procedural version of and"
  ([p s]
   {:pre [(sequential? s)]}
   (simple-andmap p s))
  ([p s0 s1 & ss]
   {:pre [(andmap sequential? (conj ss s1 s0))]}
   (andmap (fn [xs] (apply p xs)) (zip (conj ss s1 s0)))))
(defn ormap
  "The procedural version of or"
  ([p s]
   {:pre [(sequential? s)]}
   (simple-ormap p s))
  ([p s0 s1 & ss]
   {:pre [(andmap sequential? (conj ss s1 s0))]}
   (ormap (fn [xs] (apply p xs)) (zip (conj ss s1 s0)))))
