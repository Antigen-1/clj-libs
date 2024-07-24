(ns partition-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec))

(defn -main
  []
  (assert (= [[1 3] [2 4]] (partition odd? [1 2 3 4]))))
