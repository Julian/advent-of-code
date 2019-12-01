(ns day1.part2.test
  (:require [clojure.test :refer :all])
  (:require [day1.part2 :refer [fuel-for-module]]))

(deftest test-fuel-for-module
  (is (= 2 (fuel-for-module 12)))
  (is (= 2 (fuel-for-module 14)))
  (is (= 966 (fuel-for-module 1969)))
  (is (= 50346 (fuel-for-module 100756))))

(run-tests)
