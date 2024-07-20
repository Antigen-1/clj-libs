(ns clj-libs.for-fold
  (:gen-class)
  (:use clj-libs.zip-seqs clj-libs.check-duplicates clj-libs.map-utils clj-libs.fold))

(defmacro for-fold
  "Racket-style for-fold macro"
  [[& accs] [& itrs] & body]

  (doseq
   [cl (into accs itrs)]
    (assert (and (list? cl) (= (count cl) 2) (symbol? (first cl)))
            (str "Invalid accumulator or iterator:" cl)))

  (let [acc-ids (map first accs)
        inits (map second accs)
        itr-ids (map first itrs)
        seqs (map second itrs)
        acc-num (count accs)
        itr-num (count itrs)

        temp-ids (for [_ itr-ids] (gensym))
        bindings (foldl (fn [o i1 i2] (cons i1 (cons i2 o))) '() (reverse itr-ids) (reverse temp-ids))]

    (doseq
      [ids [acc-ids itr-ids]]
      (let [dups (check-duplicates ids)]
        (assert (empty? dups)
                (str "Duplicate names:" dups))))

    ;; Passes
    (defn pre [acc-ids inits itr-ids seqs body]
      (-> [acc-ids inits itr-ids seqs body]
          ((fn [[ac in ii sq bd]]
             [`[~@ac]
              `[~@in]
              `[~@ii]
              sq
              bd]))
          ((fn [[a i & rst :as all]]
             (if (= acc-num 1)
               (let [[sa] a [si] i]
                 `[~sa ~si ~@rst])
               all)))
          ((fn [[ac in ii sq bd :as all]]
             (if (= itr-num 1)
               (let [[si] ii [ss] sq]
                 `[~ac ~in ~si [~ss] ~bd])
               all)))))
    (defn guard [form]
      (-> form
          ((fn [f]
             (if (= acc-num 1)
               `(let [[o#] ~f]
                  o#)
               f)))))
    (defn post [form]
      (-> form
          ((fn [f] (if (= acc-num 1) `[~f] f)))))

    (let [[n-acc-ids ;; ~
           n-inits ;; ~
           n-itr-ids ;; ~
           n-seqs ;; ~@
           n-body ;; ~@
           ]
          (pre acc-ids inits itr-ids seqs body)
          all `all#]
      (post
       `(foldl (fn [~n-acc-ids ~n-itr-ids]
                 (let [~all (let [] ~@n-body)]
                   (assert (vector? ~all) (str "Results must be collected into a vector:" ~all))
                   (assert (= (count ~all) ~acc-num) (str "Wrong number of accumulators:" ~all))
                   ~(guard all)))
               ~n-inits
               ~@n-seqs)))))
