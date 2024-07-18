(ns clj-libs.private.simple-andmap)

(defn simple-andmap
  ([p s]
   (loop [s s r '()]
     (if (empty? s)
       (reverse r)
       (let [[fst & rst] s
             res (p fst)]
         (and res (recur rst (cons res r))))))))
