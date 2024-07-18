(ns clj-libs.private.simple-map-utils)

(defmacro ^:private map-helper
  [c p a s]
  `(loop [sq# ~s ac# ~a]
     (if (seq sq#)
       (let [[fst# & rst#] sq#
             res# (~p fst#)]
         (~c res# (recur rst# res#)))
       ac#)))

(defn simple-andmap
  [p s]
  (map-helper and p true s))
(defn simple-ormap
  [p s]
  (map-helper or p false s))
