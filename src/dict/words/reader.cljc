(ns dict.words.reader "Read files from Clojure or Planck."
  (:require
    [clojure.string :refer [split-lines]]
    #?(:cljs [planck.core :refer [slurp]])))

(defn read-lines [f]
  (->> f
       (slurp)
       (split-lines)))
