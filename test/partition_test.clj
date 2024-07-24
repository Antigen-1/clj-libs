(ns partition-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec clojure.test)
  (:require [clojure.spec.test.alpha :as stest]))

(stest/instrument 'clj-libs.spec/partition)

(deftest -main
  (is (= ['(1 3) '(2 4)] (partition odd? [1 2 3 4])))
  (is (= ['(() (1)) '([1 2])] (partition list? ['() '(1) [1 2]]))))
