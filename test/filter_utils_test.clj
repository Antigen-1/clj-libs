(ns filter-utils-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec clojure.test))

(deftest -main
  (is (= '(2 4) (filter-not odd? '(1 2 3 4))))
  (is (= '(2 4) (filter-map (fn [x] (if (odd? x) (inc x) false)) '(1 2 3 4))))
  (is (= '((2) (4)) (filter-split odd? '(1 2 3 4)))))
