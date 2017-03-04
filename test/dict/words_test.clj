(ns dict.words-test
  (:require [clojure.test :refer :all]
            [dict.words :as words]))

(deftest dinosaurs
  (testing "Has Tyrannosaurus"
    (let [words (into #{} words/dinosaurs)]
      (is (contains? words "Tyrannosaurus")))))
