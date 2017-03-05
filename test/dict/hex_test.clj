(ns dict.hex-test
  (:require [clojure.test :refer :all]
            [dict.words.hex :as hex]))

(deftest hexadecimal
  (testing "Has word Facade"
    (let [words (into #{} hex/hex-words)]
      (is (contains? words "FACADE"))))
  (testing "Hexadecimal leet uppercase has word Faceless"
    (let [words (into #{} hex/hex-words-leet-upcase)]
      (is (contains? words "FACE1E55")))))

(deftest hexadecimal-mac-addresses
  (testing "Has MAC address facade-baffle"
    (let [words (into #{} hex/mac-addresses)]
      (is (contains? words "fa-ca-de-ba-ff-1e")))))
