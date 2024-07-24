(ns partition-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec clojure.test)
  (:require [clojure.spec.test.alpha :as stest]))

(stest/instrument 'clj-libs.spec/partition)

(deftest -main
  (is (= ['(() (1)) '([1 2])] (partition list? ['() '(1) [1 2]]))))
