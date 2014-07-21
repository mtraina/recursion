(ns recursion)

(defn product [coll]
  (if (empty? coll) 1
    (* (first coll) (product (rest coll)))))

(defn singleton? [coll]
  (and
   ((complement empty?) coll)
   (empty? (rest coll))))

(defn empty-or-singleton? [a-seq]
  (or (empty? a-seq) (singleton? a-seq)))

(defn my-last [coll]
  (if (empty-or-singleton? coll) (first coll) (my-last (rest coll))))

(defn max-element [a-seq]
  (if (empty-or-singleton? a-seq) (first a-seq) (max (first a-seq) (max-element (rest a-seq)))))

(defn seq-max [seq-1 seq-2]
  (if (<= (count seq-1)(count seq-2)) seq-2 seq-1))

(defn longest-sequence [a-seq]
  (if (empty-or-singleton? a-seq) (first a-seq) (seq-max (first a-seq)(longest-sequence (rest a-seq)))))

(defn my-filter [pred? a-seq]
  (if (empty? a-seq) a-seq
    (if (pred? (first a-seq))
      (cons (first a-seq)(my-filter pred? (rest a-seq)))
      (my-filter pred? (rest a-seq)))))

(defn sequence-contains? [elem a-seq]
  (cond
   (empty? a-seq) false
   (= elem (first a-seq)) true
   :else (sequence-contains? elem (rest a-seq))))

(defn my-take-while [pred? a-seq]
  (cond
   (empty? a-seq) a-seq
   (pred? (first a-seq)) (cons (first a-seq)(my-take-while pred? (rest a-seq)))
   :else '()))

(defn my-drop-while [pred? a-seq]
  (cond
   (empty? a-seq) a-seq
   (pred? (first a-seq)) (my-drop-while pred? (rest a-seq))
   :else a-seq))

(defn seq= [a-seq b-seq]
  (cond
   (and (empty? a-seq) (empty? b-seq)) true
   (= (first a-seq) (first b-seq)) (seq= (rest a-seq) (rest b-seq))
   :else false))

(defn my-map [f seq-1 seq-2]
  (cond
    (or (empty? seq-1)(empty? seq-2)) '()
    :else (cons (f (first seq-1)(first seq-2))(my-map f (rest seq-1)(rest seq-2)))))

(defn power [n k]
  (cond
   (= n 0) 0
   (= k 0) 1
   (= k 1) n
   :else (* n (power n (dec k)))))

(defn fib [n]
  (println n)
  (cond
   (= 0 n) 0
   (= 1 n) 1
   :else (+ (fib (dec n))(fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (cond
   (>= 0 how-many-times) '()
   (= 1 how-many-times) (list what-to-repeat)
   :else (cons what-to-repeat (my-repeat (dec how-many-times) what-to-repeat))))


(defn my-range [up-to]
  (cond
   (= 0 up-to) '()
   :else (cons (dec up-to)(my-range (dec up-to)))))

(defn tails [a-seq]
  (cond
   (empty? a-seq) (cons a-seq '())
   :else (cons (list* a-seq) (tails (rest a-seq)))))

(defn inits [a-seq]
  (reverse (tails a-seq)))

(defn rotations [a-seq]
  ;(concat a-seq (rotations a-seq))
  )

(rotations [])        ;=> (())
(rotations [1 2 3])   ;=> ((1 2 3) (2 3 1) (3 1 2))
(rotations [:a :b])   ;=> ((:a :b) (:b :a))
; The order of rotations does not matter.
(rotations [:a :b])   ;=> ((:b :a) (:a :b))
(rotations [1 5 9 2]) ;=> ((1 5 9 2) (2 1 5 9) (9 2 1 5) (5 9 2 1))

(defn my-frequencies-helper [freqs a-seq]
  [:-])

(defn my-frequencies [a-seq]
  [:-])

(defn un-frequencies [a-map]
  [:-])

(defn my-take [n coll]
  [:-])

(defn my-drop [n coll]
  [:-])

(defn halve [a-seq]
  [:-])

(defn seq-merge [a-seq b-seq]
  [:-])

(defn merge-sort [a-seq]
  [:-])

(defn split-into-monotonics [a-seq]
  [:-])

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])

