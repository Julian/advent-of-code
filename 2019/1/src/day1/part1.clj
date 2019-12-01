(ns day1.part1
  (:require [clojure.edn :as edn]))

(defn fuel-for-module [mass] (- (int (/ mass 3)) 2))

(defn main
  [& args]
  (->> (line-seq (java.io.BufferedReader. *in*))
       (map edn/read-string)
       (map fuel-for-module)
       (reduce +)
       (println)))
