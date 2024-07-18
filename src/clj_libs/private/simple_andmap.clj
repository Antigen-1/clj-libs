(ns clj-libs.private.simple-andmap)

(defn simple-andmap
  ([p s]
   (reduce #(and %1 (let [r (p %2)] (and r (cons r %1)))) '() (reverse s))))
