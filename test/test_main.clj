(ns test-main
  (:require [for-fold-test :as fft]
            [check-duplicates-test :as cdt]
            [map-utils-test :as mut]
            [zip-seqs-test :as zst]
            [fold-test :as ft]
            [filter-utils-test :as fut]))

(defn -main []
  (fft/-main)
  (cdt/-main)
  (mut/-main)
  (zst/-main)
  (ft/-main)
  (fut/-main))
