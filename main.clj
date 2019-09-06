(require '[clojure.string :as str])

(def file (map read-string 
  (drop 1
  (str/split 
  (str/trim(slurp "10000.txt")) #" "))))

(defn msort1 [listy]
  (def inter (partition 2 listy))
  (def sendThis (map sort(for [thing inter] (concat (first thing) (second thing)))))
  (cond
    (= (count listy) 2) (sort (concat (first listy) (second listy)))
    (even? (count listy)) (msort1 sendThis)
    (odd? (count listy)) (msort1 (concat sendThis (list (last listy))))
  )
)

(defn msort2 [listy]
  (def inter (partition 2 listy))
  (def sendThis (pmap sort (for [thing inter] (concat (first thing) (second thing)))))
  (cond
    (= (count listy) 2) (sort (concat (first listy) (second listy)))
    (even? (count listy)) (msort2 sendThis)
    (odd? (count listy)) (msort2 (concat sendThis (list (last listy))))
  )
)

(def preParted (partition 2 file))

(dotimes [n 5] (time (msort1 preParted)))
(println)
(dotimes [n 5] (time (msort2 preParted)))

