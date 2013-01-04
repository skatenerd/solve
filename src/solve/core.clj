(ns solve.core)

(declare solve-equation)

(defn- compact [to-compact]
  (keep identity to-compact))

(defn- solver-for [variable]
  #(solve-equation variable %))

(defn- futures-for [equation]
  (let [lookup
        {
          '(= (* 2 x) 8) ['(= x 4)]
          '(= (* 3 x) 9) ['(= x 3)]
        }]
    (lookup equation)))

(defn solve-futures [variable equation]
  (let [futures (futures-for equation)
        solver (solver-for variable)
        solved-futures (map solver futures)]

    (first (compact solved-futures)))

  )

(defn solve-equation [variable equation]
  (let [[equals lhs rhs] equation]
    (if (= lhs variable)
      rhs
      (solve-futures variable equation)))
  )


(defn simplify [expression]
  'x
  )

