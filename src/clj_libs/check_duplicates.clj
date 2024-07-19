(ns clj-libs.check-duplicates
  (:gen-class))

(defn check-duplicates
  "Check duplicates in the sequence"
  [s]
  {:pre [(coll? s)]}
  (loop [old (transient #{})
         sq s
         ret (transient #{})]
    (if-let [[fst & rst] sq]
      (cond
        (contains? old fst) (recur old rst (conj! ret fst))
        :else (recur (conj! old fst) rst ret))
      (persistent! ret))))
