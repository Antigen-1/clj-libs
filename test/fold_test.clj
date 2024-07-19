(ns fold-test
  (:use clj-libs.fold))

(defn -main []
  (assert (= 10 (foldr + 0 '(0 1 2 3 4))))
  (assert (= 10 (foldr (fn [_ o] (inc o)) 0 (range 10))))
  (assert (= '(1 2) (foldr cons '() '(1 2))))
  (assert (= '(2 1) (foldl #(cons %2 %1) '() '(1 2)))))
