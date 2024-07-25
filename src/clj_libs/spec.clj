(ns clj-libs.spec
  (:gen-class)
  (:refer-clojure :exclude [partition])
  (:require [clj-libs.libs.map-utils :as mu]
            [clj-libs.libs.check-duplicates :as cd]
            [clj-libs.libs.zip-seqs :as zs]
            [clj-libs.libs.fold :as f]
            [clj-libs.libs.filter-utils :as fu]
            [clj-libs.libs.partition :as p]
            [clj-libs.libs.for-fold :as ff])
  (:require [clojure.spec.alpha :as s]))

;; Symbol table
(ff/for-fold
 [(_ nil)]
 [(sym '(andmap ormap check-duplicates zip foldr foldl filter-not filter-map filter-split partition ^:macro for-fold))
  (val [mu/andmap mu/ormap cd/check-duplicates zs/zip f/foldr f/foldl fu/filter-not fu/filter-map fu/filter-split p/partition @#'ff/for-fold])
  (var [#'mu/andmap #'mu/ormap #'cd/check-duplicates #'zs/zip #'f/foldr #'f/foldl #'fu/filter-not #'fu/filter-map #'fu/filter-split #'p/partition #'ff/for-fold])]
 [(intern 'clj-libs.spec
          (with-meta sym (meta var))
          val)])

(s/def ::pred (s/fspec :args (s/cat :value any?) :ret any?))
(s/def ::acc-or-itr (s/cat :id symbol? :value any?))

(s/fdef clj-libs.spec/andmap
  :args (s/cat :predicate ::pred :collections (s/+ coll?)))
(s/fdef clj-libs.spec/ormap
  :args (s/cat :predicate ::pred :collections (s/+ coll?)))

(s/fdef clj-libs.spec/check-duplicates
  :args (s/cat :collection coll?)
  :ret set?)

(s/fdef clj-libs.spec/zip
  :args (s/cat :collections (s/coll-of coll? :min-count 1))
  :ret coll?)

(s/fdef clj-libs.spec/foldr
  :args (s/cat :procedure (s/fspec :args (s/cat :elements (s/+ any?) :accumulator any?)) :accumulator any? :collections (s/+ coll?)))
(s/fdef clj-libs.spec/foldl
  :args (s/cat :procedure (s/fspec :args (s/cat :accumulator any? :elements (s/+ any?))) :accumulator any? :collections (s/+ coll?)))

(s/fdef clj-libs.spec/filter-not
  :args (s/cat :predicate ::pred :collection coll?)
  :ret coll?)
(s/fdef clj-libs.spec/filter-map
  :args (s/cat :predicate ::pred :collections (s/+ coll?))
  :ret coll?)
(s/fdef clj-libs.spec/filter-split
  :args (s/cat :predicate ::pred :collection coll?)
  :ret (s/coll-of list? :kind list?))

(s/fdef clj-libs.spec/partition
  :args (s/cat :predicate ::pred :collection coll?)
  :ret (s/tuple list? list?))

(s/fdef ^:macro clj-libs.spec/for-fold
  :args (s/cat :accumulators (s/coll-of ::acc-or-itr)
               :iterators (s/coll-of ::acc-or-itr)
               :body (s/+ any?)))
