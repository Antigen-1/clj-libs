(ns for-fold-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec clojure.test))

(deftest -main
  (is (= [9] (for-fold [(i 0)] ((i (range 10))) [i])))
  (is (= [285] (for-fold [(s 0)] ((i (range 10)) (n (range 10))) [(+ s (* i n))])))
  (is (= [45 10]
         (for-fold
              [(i 0) (c 0)]
              [(n (range 10))]
              [(+ i n) (+ c 1)]))))
