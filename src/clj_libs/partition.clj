(ns clj-libs.partition
  (:use clj-libs.for-fold)
  (:gen-class))

(defn part
  "Similar to filter, except that a vector of two values is returned: the items for which the predicate returns a true value, and the items for which the predicate returns false or nil"
  [p s]
  {:pre [(fn? p) (coll? s)]}
  (let [[t f]
        (for-fold
         ((t '()) (f '()))
         ((e s))
         (if (p e) [(cons e t) f] [t (cons e f)]))]
    [(reverse t) (reverse f)]))
