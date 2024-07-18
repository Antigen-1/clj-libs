(ns clj-libs.for-fold
  (:gen-class)
  (:use clj-libs.zip-seqs clj-libs.check-duplicates clj-libs.map-utils))

(defn seq-of-len?
  [l t n]
  {:pre [(and (integer? n) (pos? n))]}
  (and (t l)
       (= (count l) n)))

(defmacro for-fold
  "Racket-style for-fold macro"
  [[& accs] [& itrs] & body]

  (doseq
    [cl (into `~accs `~itrs)]
    (assert (and (seq-of-len? cl list? 2) (symbol? (first cl))) (str "Invalid accumulator or iterator:" cl)))

  (let [acc-ids (map first `~accs)
        inits (map second `~accs)
        itr-ids (map first `~itrs)
        seqs (map second `~itrs)
        len (count `~accs)

        all (into acc-ids itr-ids)
        dups (check-duplicates all)]

    (assert (empty? dups)
            (str "Duplicate names:" dups))

    `(reduce (fn [[~@acc-ids] [~@itr-ids]]
               (let [res# (let [] ~@body)]
                 (assert (seq-of-len? res# vector? ~len) (str "Invalid result:" res#))
                 res#))
             [~@inits]
             (zip (list ~@seqs)))))
