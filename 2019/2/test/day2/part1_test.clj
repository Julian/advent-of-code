(ns day2.part1-test
  (:require [clojure.test :refer :all])
  (:require [day2.part1 :refer [compute]]))

(deftest test-compute
  (is (= [2 0 0 0 99] (compute [1 0 0 0 99])))
  (is (= [2 3 0 6 99] (compute [2 3 0 3 99])))
  (is (= [2 4 4 5 99 9801] (compute [2 4 4 5 99 0])))
  (is (= [30 1 1 4 2 5 6 0 99] (compute [1 1 1 4 99 5 6 0 99]))))
