(ns map-utils-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec))

(defn -main []
  (assert (= (andmap + [1 2] [3 4]) 6))
  (assert (= (ormap + [1 2] [3 4]) 4))
  (assert (= (andmap (fn [x y] (if (= 4 (+ y x)) (- y x) false)) [1 2] '(3 4)) false))
  (assert (= (ormap (fn [x y] (if (= 1 (- y x)) (+ y x) false)) [1 3] '(2 4)) 3)))
