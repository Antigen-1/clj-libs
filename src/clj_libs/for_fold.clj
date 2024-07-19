(ns clj-libs.for-fold
  (:gen-class)
  (:use clj-libs.zip-seqs clj-libs.check-duplicates clj-libs.map-utils))

(defmacro for-fold
  "Racket-style for-fold macro"
  [[& accs] [& itrs] & body]

  (doseq
   [cl (into `~accs `~itrs)]
    (assert (and (list? cl) (= (count cl) 2) (symbol? (first cl)))
            (str "Invalid accumulator or iterator:" cl)))

  (let [acc-ids (map first `~accs)
        inits (map second `~accs)
        itr-ids (map first `~itrs)
        seqs (map second `~itrs)
        len (count `~accs)

        all (into acc-ids itr-ids)
        dups (check-duplicates all)]

    (assert (empty? dups)
            (str "Duplicate names:" dups))

    `(reduce (fn [accs# itrs#]
               (if-let [[~@acc-ids] accs#]
                 (let [[~@itr-ids] itrs#
                       res# (let [] ~@body)]
                   (assert (vector? res#) (str "Results must be collected into a vector:" res#))
                   res#)
                 (assert (= (count accs#) ~len) (str "Wrong number of accumulators:" accs#))))
             [~@inits]
             (zip (list ~@seqs)))))
