(ns day3.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn line-length [t]
  (inc (.indexOf t "\n")))

(defn split-lines [t]
  (str/split t #"\n"))

(defn replace-symbols [text]
  (str/replace text #"\W" "*"))

(defn find-symbols [line]
  (str/index-of line "*"))

(defn -main
  "I don't do a whole lot ... yet."
  [fname & args]
  (let [text (slurp fname)
        l (line-length text)
        lines (split-lines text)
        lines-spaced (map #(str/replace % "." "_") lines)
        parsed (map replace-symbols lines-spaced)
        indices (map find-symbols parsed)]
    (prn indices)))
