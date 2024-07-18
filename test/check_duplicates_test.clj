(ns check-duplicates-test
  (:use clj-libs.check-duplicates))

(defn -main []
  (assert (= (check-duplicates '(1 2 3 1)) #{1}))
  (assert (= (check-duplicates '(1 2 3)) #{})))
