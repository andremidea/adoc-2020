(ns adoc.adoc02
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))


(defn validate-password [[n c pass]]
  (let [[min-s max-s] (string/split n #"-")
        [min max] [(Integer/parseInt min-s) (Integer/parseInt max-s)]
        char (string/replace c ":" "")
        grouped (group-by identity (string/split pass #""))]

    (and (>= (-> char grouped count) min)
         (<= (-> char grouped count) max))))

(defn validate-password2 [[n c pass]]
  (let [[min-s max-s] (string/split n #"-")
        [min max] [(Integer/parseInt min-s) (Integer/parseInt max-s)]
        char (string/replace c ":" "")
        first-occurence (str (.charAt pass (dec min)))
        last-occurence (str (.charAt pass (dec max)))]
    (or (and (= char first-occurence) (not= char last-occurence))
        (and (= char last-occurence) (not= char first-occurence)))))

(defn validate-passwords []
  (let [raw-input (slurp (io/resource "input-2.txt"))
        records (->> raw-input
                     string/split-lines)
        sections (map #(string/split % #" ") records)]
    [(count (filter identity (map validate-password sections)))
     (count (filter identity (map validate-password2 sections)))]))


(validate-passwords)





