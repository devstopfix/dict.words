(ns dict.words
  (:require [clojure.string :refer [split-lines lower-case capitalize]]))

(defn- read-words [f]
  (->> f
       (slurp)
       (split-lines)
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
