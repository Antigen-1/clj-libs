(ns test-main
  (:refer-clojure :exclude [partition])
  (:use clj-libs.spec)
  (:require [clojure.spec.test.alpha :as stest])
  (:gen-class))

(defn -main []
  (for-fold
   ((_ nil))
   ((sym '(clj-libs.spec/partition
           clj-libs.spec/check-duplicates
           clj-libs.spec/filter-not clj-libs.spec/filter-map clj-libs.spec/filter-split
           clj-libs.spec/andmap clj-libs.spec/ormap
           clj-libs.spec/foldr clj-libs.spec/foldl
           ^:macro clj-libs.spec/for-fold
           clj-libs.spec/zip)))
   [(stest/check sym)]))
