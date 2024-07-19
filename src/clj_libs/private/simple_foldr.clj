(ns clj-libs.private.simple-foldr)

(defn simple-foldr
  [p i s]
  (if-let [[fst & rst] s]
    (p (simple-foldr p i rst) fst)
    i))
