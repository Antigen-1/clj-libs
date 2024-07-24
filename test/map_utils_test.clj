(ns map-utils-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec clojure.test))

(deftest -main
  (is (= (andmap + [1 2] [3 4]) 6))
  (is (= (ormap + [1 2] [3 4]) 4))
  (is (= (andmap (fn [x y] (if (= 4 (+ y x)) (- y x) false)) [1 2] '(3 4)) false))
  (is (= (ormap (fn [x y] (if (= 1 (- y x)) (+ y x) false)) [1 3] '(2 4)) 3)))
