(ns day1.part2
  (:require [clojure.edn :as edn]
            [day1.part1 :as part1]))

(defn fuel-for-module [mass]
  (reduce + (take-while pos? (drop 1 (iterate part1/fuel-for-module mass)))))

(defn main
  [& args]
  (->> (line-seq (java.io.BufferedReader. *in*))
       (map edn/read-string)
       (map fuel-for-module)
       (reduce +)
       (println)))
