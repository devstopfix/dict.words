(ns dict.hex-test
  (:require [clojure.test :refer :all]
            [dict.words.hex :as hex]))

(deftest hexadecimal
  (testing "Has word Facade"
    (let [words (into #{} hex/hex-words)]
      (is (contains? words "FACADE")))))

(deftest hexadecimal-mac-addresses
  (testing "Has MAC address facadebaff1e"
    (let [words (into #{} hex/mac-addresses)]
      (is (contains? words "fa-ca-de-ba-ff-1e")))))
