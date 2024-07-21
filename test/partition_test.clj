(ns partition-test
  (:use clj-libs.partition))

(defn -main
  []
  (assert (= [[1 3] [2 4]] (part odd? [1 2 3 4]))))
