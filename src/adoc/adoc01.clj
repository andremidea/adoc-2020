(ns adoc01.adoc01
  "Advent of code 2020 - problem 1
    https://adventofcode.com/2020/day/1"
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn expense-report []
  (let [raw-input (slurp (io/resource "input-1.txt"))
        records (->> raw-input
                     string/split-lines
                     (map #(Integer/parseInt %)))]
    (first
      (for [record records
            record2 (drop 1 records)
            :let [s (+ record record2)]
            :when (= s 2020)]
        (* record record2)))))


(defn expense-report-2 []
  (let [raw-input (slurp (io/resource "input-1.txt"))
        records (->> raw-input
                     string/split-lines
                     (map #(Integer/parseInt %)))]
    (first (for [record records
                 record2 (drop 1 records)
                 record3 (drop 2 records)
                 :let [s (+ record record2 record3)]
                 :when (= s 2020)]
             (* record record2 record3)))))

(println (expense-report))
(println (expense-report-2))
