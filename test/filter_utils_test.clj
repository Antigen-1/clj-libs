(ns filter-utils-test
  (:use clj-libs.filter-utils))

(defn -main []
  (assert (= '(2 4) (filter-not odd? '(1 2 3 4))))
  (assert (= '(2 4) (filter-map (fn [x] (if (odd? x) (inc x) false)) '(1 2 3 4))))
  (assert (= ['(1 3) '(2 4)] (filter-split odd? '(1 2 3 4)))))
