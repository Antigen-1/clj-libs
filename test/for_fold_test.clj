(ns for-fold-test
  (:use [clj-libs.for-fold :only (for-fold)]))

(defn -main []
  (assert (= [9] (for-fold [(i 0)] ((i (range 10))) [i])))
  (assert (= [45 10]
             (for-fold
              [(i 0) (c 0)]
              [(n (range 10))]
              [(+ i n) (+ c 1)]))))
