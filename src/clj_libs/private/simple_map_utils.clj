(ns clj-libs.private.simple-map-utils)

(defmacro ^:private map-helper
  [c p a s]
  `(loop [sq# ~s ac# ~a]
     (if-let [[fst# & rst#] sq#]
       (let [res# (~p fst#)]
         (~c res# (recur rst# res#)))
       ac#)))

(defn simple-andmap
  [p s]
  (map-helper and p true s))
(defn simple-ormap
  [p s]
  (map-helper or p false s))
