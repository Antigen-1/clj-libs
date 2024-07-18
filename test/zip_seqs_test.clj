(ns zip-seqs-test
  (:use clj-libs.zip-seqs))

(defn -main []
  (assert (= (for [x (zip [[1 2 3] [4 5 6] [7 8 9]])] x)
             '((1 4 7) (2 5 8) (3 6 9)))))
