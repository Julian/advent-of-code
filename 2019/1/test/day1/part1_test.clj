(ns day1.part1.test
  (:require [clojure.test :refer :all])
  (:require [day1.part1 :refer [fuel-for-module]]))

(deftest test-fuel-for-module
  (is (= 2 (fuel-for-module 12)))
  (is (= 2 (fuel-for-module 14)))
  (is (= 654 (fuel-for-module 1969)))
  (is (= 33583 (fuel-for-module 100756))))

(run-tests)
