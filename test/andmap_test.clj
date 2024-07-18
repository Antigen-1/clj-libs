(ns andmap-test
  (:use clj-libs.andmap))

(defn -main []
  (assert (= (andmap + [1 2]) '(1 2)))
  (assert (= (andmap + [1 2] [3 4]) '(4 6)))
  (assert (= (andmap + [1 2] '(3 4)) '(4 6))))
