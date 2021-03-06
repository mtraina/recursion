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
   (not= (count a-seq)(count b-seq)) false
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
  (cond
   (empty? a-seq) (list (list))
   :else (cons (list* a-seq) (inits (butlast a-seq)))))

(defn rot [n a-seq]
  (if (> n 0)
    (cons (concat (drop n a-seq)(take n a-seq))(rot (dec n) a-seq))))

(defn rotations [a-seq]
  ;(let [rota (fn [n the-seq] (if (< n (count a-seq))(cons (concat (drop n a-seq)(take n a-seq))(rot (inc n) a-seq))))])
  (if (empty? a-seq) (list (list))
    (rot (count a-seq) a-seq)))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq) freqs
   (let [fst (first a-seq)
         freq (get freqs fst)]
     (my-frequencies-helper (assoc freqs fst (if (nil? freq) 1 (inc freq))) (rest a-seq)))))

(defn my-frequencies [a-seq]
  (my-frequencies-helper {} a-seq))

(defn un-frequencies-helper [unfreqs a-map]
  (if (empty? a-map) unfreqs
    (let [fst (first a-map)
          unfreq (repeat (val fst) (key fst))]
      (un-frequencies-helper (concat unfreq unfreqs) (rest a-map)))))

(defn un-frequencies [a-map]
  (un-frequencies-helper '() a-map))

(defn my-take-helper [taken n coll]
  (if (or (empty? coll)(= 0 n)) taken
    (my-take-helper (concat taken (list (first coll))) (dec n) (rest coll))))

(defn my-take [n coll]
  (my-take-helper '() n coll))

(defn my-drop [n coll]
  (if (or (empty? coll) (= 0 n)) coll
    (my-drop (dec n)(rest coll))))

(defn halve-helper [fst n a-seq]
  (cond
   (empty? a-seq) (vector fst a-seq)
   (= 0 n)(vector fst a-seq)
   :else (halve-helper (concat fst (list (first a-seq))) (dec n) (rest a-seq))))

(defn halve [a-seq]
  (halve-helper '() (int (/ (count a-seq) 2)) a-seq))

(defn improved<= [a b]
  (cond
   (nil? a) false
   (nil? b) true
   :else (<= a b)))

(defn seq-merge-helper [sorted-seq a-seq b-seq]
  (cond
   (and (empty? a-seq)(empty? b-seq)) sorted-seq
   (improved<= (first a-seq)(first b-seq)) (seq-merge-helper (concat sorted-seq (list (first a-seq))) (rest a-seq) b-seq)
   :else (seq-merge-helper (concat sorted-seq (list (first b-seq))) a-seq (rest b-seq))))

(defn seq-merge [a-seq b-seq]
  (seq-merge-helper '() a-seq b-seq))

(defn merge-sort [a-seq]
  (if (empty-or-singleton? a-seq) a-seq
    (let [halves (halve a-seq)]
      (seq-merge (merge-sort (first halves)) (merge-sort (last halves))))))

(defn split-helper [result part a-seq]
  (cond
   (empty? a-seq) (concat result (list part))
   (or (empty? part)
        (nil? (second part))
        (<= (first part)(last part)(first a-seq))
        (>= (first part)(last part)(first a-seq)))
          (split-helper result (concat part (list(first a-seq))) (rest a-seq))
   :else (split-helper (concat result (list part)) '() a-seq)))

(defn split-into-monotonics [a-seq]
  (split-helper '() '() a-seq))

(defn permutation-helper [a-set]
  (cond
   (<= (count a-set) 2) (rotations a-set)
   :else (rotations (rest a-set))))

(defn permutations [a-set]
  (permutation-helper a-set))

(defn powerset [a-set]
  [:-])

