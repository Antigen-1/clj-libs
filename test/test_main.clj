(ns test-main
  (:require [for-fold-test :as fft]
            [check-duplicates-test :as cdt]
            [andmap-test :as at]
            [zip-seqs-test :as zst]
            [foldr-test :as ft]))

(defn -main []
  (fft/-main)
  (cdt/-main)
  (at/-main)
  (zst/-main)
  (ft/-main))
