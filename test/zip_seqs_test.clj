(ns zip-seqs-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec clojure.test)
  (:require [clojure.spec.test.alpha :as stest]))

(stest/instrument 'clj-libs.spec/zip)

(deftest -main
  (is (= (for [x (zip [[1 2 3] [4 5 6] [7 8 9]])] x)
         '((1 4 7) (2 5 8) (3 6 9)))))
