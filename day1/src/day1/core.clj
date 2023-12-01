(ns day1.core
  (:gen-class)
  (:require [clojure.string :as string]))

(def digs {"1" "one"
           "2" "two"
           "3" "three"
           "4" "four"
           "5" "five"
           "6" "six"
           "7" "seven"
           "8" "eight"
           "9" "nine"})

(defn replace-digs [l m]
  (reduce #(string/replace %1 (val %2) (str (val %2) (key %2) (val %2))) l m))

(defn solve-line [l]
  (let [digits (filter #(Character/isDigit %) l)]
    (Integer/parseInt (str (first digits) (last digits)))))

(defn solve [s]
  (reduce + (map solve-line s)))

(defn solve-2 [s m]
  (reduce + (map #(solve-line (replace-digs % m)) s)))

(defn -main
  "I don't do a whole lot ... yet."
  [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (let [lines (line-seq r)]
      (println (solve lines))
      (println (solve-2 lines digs)))))
