(ns dict.words-test
  (:require [clojure.test :refer :all]
            [dict.words :as words]))

(deftest dinosaurs
  (testing "Has Tyrannosaurus"
    (is (contains? (into #{} words/dinosaurs) "Tyrannosaurus"))))
