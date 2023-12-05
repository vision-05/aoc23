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

(defn is-impossible? [l]
  (filter #(neg? (val %)) (merge-with - {\b 14 \r 12 \g 13} (merge-maps l))))

(defn conj-idx [a i]
  (conj a i))

(defn -main
  "I don't do a whole lot ... yet."
  [fname]
  (with-open [r (clojure.java.io/reader fname)]
    (let [lines (line-seq r)]
      (prn (reduce + (flatten (filter (fn [c] (= (count c) 1)) (map conj-idx (map #(is-impossible? (parse-line % tokens)) lines) (map inc (range (count lines)))))))))))
