(ns solve.core-test
  (:use clojure.test
        solve.core))

(deftest one-variable
  (is (= 4 (solve-equation
             'x
             '(= x 4)
           )))
  (is (= 8 (solve-equation
             'x
             '(= x 8)
           )))
  (is (= 4 (solve-equation
             'x
             '(= (* 2 x) 8)
            )))
  (is (= 3 (solve-equation
             'x
             '(= (* 3 x) 9)
            )))
  (is (= 3 (solve-equation
             'x
             '(= (* x 3) 9)
            )))
 )

(deftest simplification
  (is (= 'x (simplify
              '(/ 2 (* 2 x))
      )))
  (is (= 'x (simplify
              '(* 2 (/ x 2))
      )))

         )
