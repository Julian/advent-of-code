(ns day2.part1
  (:require [clojure.edn :as edn]))

(defn tape-add [tape x y result]
  (assoc tape result (+ (tape x) (tape y))))

(defn tape-mul [tape x y result]
  (assoc tape result (* (tape x) (tape y))))

(defn compute
  ([tape] (compute tape 0))
  ([noun verb tape] (compute (assoc tape 1 noun 2 verb)))
  ([tape instruction-pointer]
   (let [opcode (tape instruction-pointer)]
     (if (= opcode 99)
       tape
       (let [[_ x y result & tape-tail] (subvec tape instruction-pointer)]
         (recur
           (case opcode
             1 (tape-add tape x y result)
             2 (tape-mul tape x y result))
           (+ instruction-pointer 4)))))))

(defn input-to-vector []
  (->> (slurp *in*)
       (clojure.string/trim-newline)
       (#(clojure.string/split %1 #","))
       (map edn/read-string)
       (apply vector)))

(defn main
  [& args]
  (first (compute 12 2 (input-to-vector))))
