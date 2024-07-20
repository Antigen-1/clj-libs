(ns clj-libs.for-fold
  (:gen-class)
  (:use clj-libs.zip-seqs clj-libs.check-duplicates clj-libs.map-utils clj-libs.fold))

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

        temp-ids (for [_ itr-ids] (gensym))
        bindings (foldl (fn [o i1 i2] (cons i1 (cons i2 o))) '() (reverse itr-ids) (reverse temp-ids))]

    (doseq
      [ids [acc-ids itr-ids]]
      (let [dups (check-duplicates ids)]
        (assert (empty? dups)
                (str "Duplicate names:" dups))))

    `(foldl (fn [accs# ~@temp-ids]
              (if-let [[~@acc-ids] accs#]
                (let [res# (let [~@bindings] ~@body)]
                  (assert (vector? res#) (str "Results must be collected into a vector:" res#))
                  res#)
                (assert (= (count accs#) ~len) (str "Wrong number of accumulators:" accs#))))
            [~@inits]
            ~@seqs)))
