(ns foldr-test
  (:use clj-libs.foldr))

(defn -main []
  (assert (= 10 (foldr + 0 '(0 1 2 3 4))))
  (assert (= 10 (foldr (fn [o _] (inc o)) 0 (range 10)))))
