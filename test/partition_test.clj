(ns partition-test
  (:require [clj-libs.partition :as cp]))

(defn -main
  []
  (assert (= [[1 3] [2 4]] (cp/partition odd? [1 2 3 4]))))
