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
              (contains? words "eclair"))))
      (testing "Without 1 letter words"
        (is (not (contains? words "a"))))
      (testing "Without 2 letter words"
        (is (not (contains? words "be"))))
      (testing "Planck"
        (let [ws (filter (partial re-find #"^planck") words/words)]
          (is (contains? #{"planckian" "planck"} (first ws) ))))))

(deftest ascii-words
  (let [words (into #{} words/ascii-words)]
    (testing "Without French words"
      (is (empty? (filter (partial re-find #"é") words))))))

(deftest word-lengths
  (testing "Short words"
      (is (<= (-> words/short-ascii-words (shuffle) (first) (.length)) 8))))
  ; (testing "Long words"
  ;     (is (<= (-> words/short-words (shuffle) (first)) 8))))

(deftest adjectives
  (let [words (into #{} words/adjectives)]
    (testing "Subset"
      (is (< (count words/adjectives) (count words/words))))
    (testing "-ible adjectives"
      (is (contains? words "incredible")))
    (testing "Filter other adjectives"
      (is (not (contains? words "heavy"))))))

(deftest domains
  (testing "-dom suffix"
      (for [w words/domains]
        (is (.endsWith w "dom")))))
