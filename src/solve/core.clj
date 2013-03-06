(ns solve.core)

(declare solve-equation solve-futures new-expression futures-for solver-for compact all-numeric?)

(defprotocol Expression
  (operator [this])
  (arguments [this])
  (evaluate [this]))

(defprotocol Solvable
  (solve-for [this target-variable]))

(defrecord Leaf [leaf]
  Expression
  (evaluate [this] leaf)
  (operator [this] 'identity)
  (arguments [this] this))
  
(defrecord MathSyntaxTree [s-expression]
  Expression
  (operator [this] (new-expression (first s-expression)))
  (arguments [this] (map new-expression (rest s-expression)))
  (evaluate [this] (if (all-numeric? (arguments this))
                     (new-expression (apply (eval (operator this)) (arguments this)))
                     this)))

(defrecord MathEquation [lhs rhs]
  Expression
  (operator [this] '=)
  (arguments [this] (list lhs rhs))
  Solvable
  (solve-for  [this target-variable] (solve-equation (list '= lhs rhs) target-variable)))

(def operator-opposites 
  {
   '+ '-
   '- '+
   '* '/
   '/ '*
  })

(defn opposite-operators? [first-operator second-operator]
  (= (operator-opposites first-operator) second-operator))

(defn new-expression [s-expression]
  (if (seq? s-expression)
    (if (= '= (first s-expression))
      (MathEquation. (first s-expression) (second s-expression))
      (MathSyntaxTree. s-expression))
    (Leaf. s-expression)))

(defn solve-equation [equation variable]
  22)

(defn- all-numeric? [to-check-numericality]
  (every? number? to-check-numericality))

;  (let [[equals lhs rhs] equation]
;    (if (= lhs (new-expression variable))
;      rhs
;      (solve-futures variable equation)))
;  )

;(defn simplify [expression]
;  (if (number? expression)
;    expression
;    (let [simplified-args (map simplify (arguments expression))]
;      (if (every? number? simplified-args)
;        (evaluate expression)
;        expression))))
;
;(defn solve-futures [variable equation]
;  (let [futures (futures-for equation)
;        solver (solver-for variable)
;        solved-futures (map solver futures)]
;
;    (first (compact solved-futures)))
;
;  )
;
;;(defmacro must-be-seq [arg & exprs]
;;  `(if (seq? ~arg)
;;    (do ~@exprs)
;;    ~arg))
;
;
;(defn- compact [to-compact]
;  (keep identity to-compact))
;
;(defn- solver-for [variable]
;  #(solve-equation % variable))
;
;(defn- futures-for [equation]
;  (let [lookup
;        {
;          '(= (* 2 x) 8) ['(= x 4)]
;          '(= (* 3 x) 9) ['(= x 3)]
;        }]
;
;    (lookup equation)))
;
;;
;;
;;(defn simplify-division-identity [expression]
;;  (must-be-seq expression
;;    (let [[op first-arg second-arg] expression]
;;      (if (and (= op '/) (= 1 second-arg))
;;        first-arg
;;        expression))))
;;
;;(defn- only-non-1-element [to-search]
;;  (let [filtered (filter #(not (= % 1)) to-search)]
;;    (if (= 1 (count filtered))
;;      (first filtered))))
;;
;;(defn simplify-multiplicative-identity [expression]
;;  (must-be-seq expression
;;    (let [[op first-arg second-arg] expression]
;;      (if (and (= op '*) (only-non-1-element (rest expression)))
;;        (only-non-1-element (rest expression))
;;        expression))))
;;
;;(defn mult-cancelable? [expression]
;;  (= expression '(/ (* 2 x) 2)))
;;
;;(defn get-scalar [expression]
;;  (if (seq? expression)
;;    (first (filter number? expression))
;;    expression))
;;
;;(defn simplify-division-cancel [expression]
;;  (if (mult-cancelable? expression)
;;    (let [ [op first-arg second-arg] expression
;;           first-scalar (get-scalar first-arg)
;;           second-scalar (get-scalar second-arg)
;;           new-scalar (/ first-scalar second-scalar)
;;          ]
;;      (list '/ 'x new-scalar)
;;    )
;;    expression
;;    ))
;;
;;(defn simplify-identity [expression]
;;  (-> expression simplify-multiplicative-identity simplify-division-identity))
;;
;;(defn simplify [expression]
;;  (-> expression simplify-division-cancel simplify-identity)
;;  )
;;
