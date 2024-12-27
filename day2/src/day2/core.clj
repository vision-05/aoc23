(ns day2.core
  (:gen-class)
  (:require [clojure.string :as string]))

(def tokens #{\g \b \r \; \, \:})

(defn parse-line [l u]
  (filter #(or (Character/isDigit %) (contains? u %)) (string/replace l "green" "g")))

(defn split-line [l]
  (rest (string/split (apply str l) #"[;:,]")))

(defn make-map-entry [s]
  {(last s) (apply str (butlast s))})

(defn make-maps [l]
  (println (split-line l))
  (map make-map-entry (split-line l)))

(defn all->int [m]
  (map #(update-vals % (fn [a] (Integer/parseInt (str a)))) m))

(defn merge-maps [l]
  (apply merge-with max (all->int (make-maps l))))

(defn filter-impossible [l]
  (filter #(neg? (val %)) (merge-with - {\b 14 \r 12 \g 13} l)))

(defn find-max [l]
  (apply merge {\b 0 \r 0 \g 0} l))

(defn conj-idx [a i]
  (conj a i))

(defn -main
  "I don't do a whole lot ... yet."
  [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (let [lines (line-seq r)
          parsed (map #(parse-line % tokens) lines)
          lexed (map merge-maps parsed)
          filtered (map filter-impossible lexed)
          indices (map inc (range (count lines)))
          indexed (map conj-idx filtered indices)
          any-val (filter #(= (count %) 1) indexed)
          sum-tot (reduce + (flatten any-val))
          max-cols (map find-max lexed)
          mul-games (map #(reduce * (vals %)) max-cols)
          powers (reduce + mul-games)]
      (prn sum-tot)
      (prn powers))))
