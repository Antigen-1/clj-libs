(ns check-duplicates-test
  (:refer-clojure :exclude [partition])
  (:use clj-libs.lib))

(defn -main []
  (assert (= (check-duplicates '(1 2 3 1)) #{1}))
  (assert (= (check-duplicates '(1 2 3)) #{})))
