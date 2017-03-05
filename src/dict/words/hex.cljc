(ns dict.words.hex
  (:require [dict.words :as words]))

(def hex-words
  "List of words formed of hexadecimal characters"
  (->> words/words
       (filter #(re-find #"^[a-f]{2,}$" %))
       (map clojure.string/upper-case)
       (sort-by count)
       (reverse)
       (lazy-seq)))

; MAC Addresses - https://en.wikipedia.org/wiki/MAC_address

(defn swap-letter-digit [x d]
  (fn [s] (clojure.string/replace s x d)))

(def hex-words-2
  "List of words with some digits as letters"
  (->> words/words
       (map (swap-letter-digit "l" "1"))
       (filter (partial re-find #"^[a-f1]{3,}$"))))

(defn byte-pairs [s]
  "Convert a string of 00112233... to 00-11-22-33"
  (->> s
       (partition 2)
       (interleave (repeat "-"))
       (flatten)
       (rest)
       (clojure.string/join)))

; https://en.wikipedia.org/wiki/MAC_address#Notational_conventions
(def mac-addresses
  "Words which form MAC addresses (e.g. fa-ca-de-ba-ff-1e)"
  (let [words-6 (->> hex-words-2 (filter #(>= (count %) 4)))]
    (for [w1 words-6 w2 words-6 :when (= 12 (+ (count w1) (count w2)))]
      (byte-pairs (str w1 w2)))))
