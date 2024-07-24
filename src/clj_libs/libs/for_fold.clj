(ns clj-libs.libs.for-fold
  (:use clj-libs.libs.zip-seqs clj-libs.libs.check-duplicates clj-libs.libs.map-utils clj-libs.libs.fold))

(defmacro for-fold
  "Racket-style for-fold macro"
  [[& accs] [& itrs] body0 & body]

  (doseq
   [cl (into accs itrs)]
    (assert (and (list? cl) (= (count cl) 2) (symbol? (first cl)))
            (str "Invalid accumulator or iterator:" cl)))

  (let [acc-ids (map first accs)
        inits (map second accs)
        itr-ids (map first itrs)
        seqs (map second itrs)
        acc-num (count accs)]

    (doseq
      [ids [acc-ids itr-ids]]
      (let [dups (check-duplicates ids)]
        (assert (empty? dups)
                (str "Duplicate names:" dups))))

    ;; Passes
    (defn pre [acc-ids inits itr-ids seqs body v-ass? c-ass?]
      (-> [acc-ids inits itr-ids seqs body v-ass? c-ass?]
          ((fn [[ac in ii sq bd vs cs]]
             [`[~@ac]
              `[~@in]
              ii
              sq
              bd
              vs
              cs]))

          ((fn [[a i & rst :as all]]
             (if (= acc-num 1)
               `[~@a ~@i ~@rst]
               all)))
          ((fn [[ac in ii sq bd vs cs :as all]]
             (let [extract
                   (fn [tail]
                     `(let [[o# & r# :as a#] ~tail]
                        (assert (vector? a#) (str "Results must be collected into a vector:" a#))
                        (assert (empty? r#) (str "Wrong number of accumulators:" a#))
                        o#))
                   before-tail (drop-last bd)
                   tail (last bd)]
               (cond
                 (and (= acc-num 1) (vector? tail) (= (count tail) 1))
                 `[~ac ~in ~ii ~sq [~@before-tail ~@tail] false false]
                 (= acc-num 1)
                 `[~ac ~in ~ii ~sq [~@before-tail ~(extract tail)] false false]
                 :else all))))))
    (defn post [form]
      (-> form
          ((fn [f] (if (= acc-num 1) `[~f] f)))))

    (let [[n-acc-ids ;; ~
           n-inits ;; ~
           n-itr-ids ;; ~@
           n-seqs ;; ~@
           n-body ;; ~@
           n-v-ass?
           n-c-ass?]
          (pre acc-ids inits itr-ids seqs (cons body0 body) true true)
          all `all#]
      (post
       `(foldl (fn [~n-acc-ids ~@n-itr-ids]
                   (let [~all (let [] ~@n-body)]
                     ~@(if n-v-ass?
                         `((assert (vector? ~all) (str "Results must be collected into a vector:" ~all)))
                         `())
                     ~@(if n-c-ass?
                         `((assert (= (count ~all) ~acc-num) (str "Wrong number of accumulators:" ~all)))
                         `())
                     ~all))
               ~n-inits
               ~@n-seqs)))))
