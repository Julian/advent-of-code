(ns day3.part1
  (:require [clojure.edn :as edn]
            [clojure.set :use union]))

(defn closest-crossing-distance [first-wire second-wire]
  ;; filter out [0 0]
  (second (filter
            identity
            (map (fn [[distance points]]
                   (when (seq (clojure.set/intersection
                                points
                                (second-wire distance)))
                     distance))
                 first-wire))))

(defn taxicab-norm [[x y]] (+ (Math/abs x) (Math/abs y)))

(defn wire-from-strings
  ([wire-as-strings] (wire-from-strings wire-as-strings [0 0] {}))
  ;; when-let
  ([[[direction & str-ide] & more] [x y] distance-to-points]
   ;; this seems nonideal but apparently you can't have multiple overloads and
   ;; dispatch purely by pattern :/
   ;; But if we hit this clause, we've bottomed out.
   (if (nil? direction)
     (into (sorted-map) (sort-by first (seq distance-to-points)))
     ;; surely there's a way to avoid this...
     (let [stride (edn/read-string (apply str str-ide))
           [end segment]
           (case direction
             \U [[x (+ y stride)] (map vector (repeat x) (range y (+ y stride 1)))]
             \D [[x (- y stride)] (map vector (repeat x) (range (- y stride) y))] 
             \L [[(- x stride) y] (map vector (range (- x stride) x) (repeat y))] 
             \R [[(+ x stride) y] (map vector (range x (+ x stride 1)) (repeat y))])]
       (wire-from-strings
         more
         end
         (apply merge-with union distance-to-points (map #(hash-map (taxicab-norm %1) #{%1}) segment)))))))

(defn input-to-wires []
  (->> (slurp *in*)
       (clojure.string/split-lines)
       (map #(clojure.string/split %1 #","))
       (map wire-from-strings)))

(defn main
  [& args]
  (apply closest-crossing-distance (input-to-wires)))
