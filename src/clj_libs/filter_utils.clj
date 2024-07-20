(ns clj-libs.filter-utils
  (:use clj-libs.for-fold clj-libs.map-utils clj-libs.zip-seqs))

(defn filter-not
  "Like filter, except that elements are preserved when the predicate returns false or nil"
  [p s]
  {:pre [(fn? p) (coll? s)]}
  (filter (comp not p) s))
(defn filter-map
  "Like map, except that, if the predicate returns false or nil, that element is omitted from the resulting collection"
  ([proc coll]
   {:pre [(fn? proc) (coll? coll)]}
   (filter (fn [x] x) (map proc coll)))
  ([proc coll0 coll1 & colls]
   {:pre [(fn? proc) (andmap coll? (cons coll0 (cons coll1 colls)))]}
   (filter-map (fn [l] (apply proc l)) (zip (cons coll0 (cons coll1 colls))))))
(defn filter-split
  "Split the collection according to the result of the predicate"
  [p s]
  {:pre [(fn? p) (coll? s)]}
  (let [[t f]
        (for-fold
         ((t '()) (f '()))
         ((e s))
         (if (p e) [(cons e t) f] [t (cons e f)]))]
    [(reverse t) (reverse f)]))
