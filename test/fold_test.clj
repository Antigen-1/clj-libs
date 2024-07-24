(ns fold-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec clojure.test))

(deftest -main
  (is (= 10 (foldr + 0 '(0 1 2 3 4))))
  (is (= 10 (foldr (fn [_ o] (inc o)) 0 (range 10))))
  (is (= '(1 2) (foldr cons '() '(1 2))))
  (is (= '(2 1) (foldl #(cons %2 %1) '() '(1 2)))))
