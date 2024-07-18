(ns clj-libs.check-duplicates
  (:gen-class))

(defn check-duplicates
  "Check duplicates in the sequence"
  [s]
  {:pre [(coll? s)]}
  (loop [old (transient #{})
         sq s
         ret (transient #{})]
    (cond
      (empty? sq) (persistent! ret)
      (contains? old (first sq)) (recur old (rest sq) (conj! ret (first sq)))
      :else (recur (conj! old (first sq)) (rest sq) ret))))
