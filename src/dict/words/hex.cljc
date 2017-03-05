(ns dict.words.hex
  (:require [dict.words :as words]))

(def hex-words
  "List of words formed of hexadecimal characters"
  (->> words/words
       (filter (partial re-find #"^[A-Z]?[a-f]{2,}$"))
       (map clojure.string/upper-case)
       (sort-by count)
       (reverse)
       (lazy-seq)))


(defn- swap-letter-digit [x d]
  (fn [s] (clojure.string/replace s x d)))

(def hex-words-leet
  "List of lowercase words with some digits as letters.
   See https://simple.wikipedia.org/wiki/Leet#Numbers_for_letters"
  (->> words/words
       (map (swap-letter-digit "l" "1"))
       (filter (partial re-find #"^[a-f1]{3,}$"))
       (lazy-seq)))

(def hex-words-leet-upcase
  "List of uppervase words with some digits as letters.
   See https://simple.wikipedia.org/wiki/Leet#Numbers_for_letters"
  (->> words/words
       (map clojure.string/upper-case)
       (map (swap-letter-digit "L" "1"))
       (map (swap-letter-digit "S" "5"))
       (filter (partial re-find #"^[A-F15]{3,}$"))
       (lazy-seq)))

; MAC Addresses - https://en.wikipedia.org/wiki/MAC_address

(defn- byte-pairs [s]
  "Convert a string of 00112233... to 00-11-22-33"
  (->> s
       (partition 2)
       (interleave (repeat "-"))
       (flatten)
       (rest)
       (clojure.string/join)))

; https://en.wikipedia.org/wiki/MAC_address#Notational_conventions
(def mac-addresses
  "Pairs of words which form MAC addresses (e.g. fa-ca-de-ba-ff-1e)
   using Leet"
  (let [words-4? (words/size-predicate 4 >=)
        words-4 (->> hex-words-leet (filter words-4?))]
    (->
      (for [w1 words-4 w2 words-4 :when (= 12 (+ (count w1) (count w2)))]
          (byte-pairs (str w1 w2)))
      (lazy-seq))))
