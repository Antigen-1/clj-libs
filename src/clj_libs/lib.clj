(ns clj-libs.lib
  (:gen-class)
  (:refer-clojure :exclude [partition])
  (:use clj-libs.map-utils
        clj-libs.check-duplicates
        clj-libs.for-fold
        clj-libs.zip-seqs
        clj-libs.fold
        clj-libs.filter-utils
        clj-libs.partition))
