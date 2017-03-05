(ns dict.words-test
  (:require [clojure.test :refer :all]
            [dict.words :as words]))

(deftest dinosaurs
  (testing "Has Tyrannosaurus"
    (let [words (into #{} words/dinosaurs)]
      (is (contains? words "Tyrannosaurus")))))

(deftest words
    (let [words (into #{} words/words)]
      (testing "Without posessives"
        (is (not (contains? words "atomizer's"))))
      (testing "With accute"
        (is (or
              (contains? words "éclair")
              (contains? words "eclair"))))))
