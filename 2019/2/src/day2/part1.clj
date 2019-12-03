(ns day2.part1
  (:require [clojure.edn :as edn]))

(defn tape-add [tape x y result]
  (assoc tape result (+ (tape x) (tape y))))

(defn tape-mul [tape x y result]
  (assoc tape result (* (tape x) (tape y))))

(defn compute
  ([tape] (compute tape 0))
  ([tape tape-head]
   (let [opcode (tape tape-head)]
     (if (= opcode 99)
       tape
       (let [[_ x y result & tape-tail] (subvec tape tape-head)]
         (recur
           (case opcode
             1 (tape-add tape x y result)
             2 (tape-mul tape x y result))
           (+ tape-head 4)))))))

(defn main
  [& args]
  (->> (slurp *in*)
       (clojure.string/trim-newline)
       (#(clojure.string/split %1 #","))
       (map edn/read-string)
       (#(assoc (apply vector %1) 1 12 2 2))
       (compute)
       (first)
       (println)))
