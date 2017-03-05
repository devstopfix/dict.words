(ns dict.words
  (:require [clojure.string :refer [lower-case capitalize]]
            [dict.words.reader :refer [read-lines]]))

(defn- read-words [f]
  (->> f
       (read-lines)
       (map lower-case)
       (into (sorted-set))))

(def possesive? (partial re-find #"'"))

(def words
  "Seq of words without possesives. Ubuntu wbritish package has words such as éclair and soirée's"
  (->> "/usr/share/dict/words"
       (read-words)
       (remove possesive?)
       (lazy-seq)))

(def ascii-words
  "Words consisting only of ASCII letters, without Ubuntu wbritish words such as éclair and soirée"
  (->> words
       (filter (partial re-matches #"^[a-z]+$"))
       (lazy-seq)))

(def dinosaurs
  "Dinosaur names"
  (->> words
       (filter (partial re-find #"aurus$"))
       (map clojure.string/capitalize)))
