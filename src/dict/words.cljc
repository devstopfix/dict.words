(ns dict.words
  (:require [clojure.string :refer [lower-case capitalize]]
            [dict.words.reader :refer [read-lines]]))

(defn- read-words [f]
  (->> f
       (read-lines)
       (map lower-case)
       (into (sorted-set))))

(def words
  (-> "/usr/share/dict/words"
      (read-words)
      (lazy-seq)))

(def dinosaurs
  (->> words
       (filter (partial re-find #"aurus$"))
       (map clojure.string/capitalize)))
