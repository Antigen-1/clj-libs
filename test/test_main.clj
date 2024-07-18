(ns test-main
  (:require [for-fold-test :as fft]
            [check-duplicates-test :as cdt]
            [map-utils-test :as mut]
            [zip-seqs-test :as zst]
            [foldr-test :as ft]))

(defn -main []
  (fft/-main)
  (cdt/-main)
  (mut/-main)
  (zst/-main)
  (ft/-main))
