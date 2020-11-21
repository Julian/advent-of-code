(ns day2.part2
  (:require [day2.part1 :as part1]
            [clojure.math.combinatorics :as combo]))

(def nouns-and-verbs (combo/cartesian-product (range 100) (range 100)))

(defn is-desired-tape? [tape] (= (first tape) 19690720))

(defn main
  [& args]
  (let [tape (day2.part1/input-to-vector)]
    (->> nouns-and-verbs
        (map (fn [[noun verb]] (day2.part1/compute noun verb tape)))
        (filter is-desired-tape?)
        (first)
        ((fn [[_ noun verb]] (+ (* 100 noun) verb))))))
