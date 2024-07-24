(ns check-duplicates-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec))

(defn -main []
  (assert (= (check-duplicates '(1 2 3 1)) #{1}))
  (assert (= (check-duplicates '(1 2 3)) #{})))
