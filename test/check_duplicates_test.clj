(ns check-duplicates-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec clojure.test)
  (:require [clojure.spec.test.alpha :as stest]))

(stest/instrument 'clj-libs.spec/check-duplicates)

(deftest -main
  (is (= (check-duplicates '(1 2 3 1)) #{1}))
  (is (= (check-duplicates '(1 2 3)) #{})))
