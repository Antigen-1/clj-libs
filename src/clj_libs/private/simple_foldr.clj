(ns clj-libs.private.simple-foldr)

(defn simple-foldr
  [p i s]
  (if (seq s)
    (p (simple-foldr p i (rest s)) (first s))
    i))
