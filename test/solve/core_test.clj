(ns solve.core-test
  (:use clojure.test
        solve.core))

(deftest solve
  (testing "data structures"

    (testing "unary expression"
      (let [unary (new-expression 2)]
        (is (= 'identity (operator unary)))
        (is (= unary (arguments unary)))))

    (testing "simple expression"
      (let [expression (new-expression '(+ 5 2))]
        (is (= (new-expression '+) (operator expression)))
        (is (= (new-expression 5) (first (arguments expression))))
        (is (= (new-expression 2) (second (arguments expression))))

;      (is (= (new-expression 1)  (first (arguments expression))))
;      (is (= (new-expression 2)  (second (arguments expression))))))
))))
;  (testing "complex expression"
;           (let [complex (new-expression '(+ (* 4 3) 2))]
;             (is (= (new-expression '+) (operator complex)))
;             (is (= 2 (last (arguments complex))))
;             (let [first-argument (first (arguments complex))]
;               (is (= (new-expression '(* 4 3)) first-argument))
;               (is (= '* (operator first-argument)))
;               (is (= '(4 3) (arguments first-argument)))
;               )))
;  (testing "operators"
;     (is (opposite-operators? '+ '-))
;     (is (opposite-operators? '- '+))
;     (is (opposite-operators? '* '/))
;     (is (not (opposite-operators? '* '+)))))
;
;(testing "solve equations"
;  (deftest one-variable
;    (let [equation (new-expression '(= x 4))]
;      (is (= (new-expression 4) (solve-for
;                 equation
;                 'x))))))
;
;(testing "simplify equations"
         ;)




         ;goal
;  (let [equation (new-equation '(* 4 x) 16)]
;    (is (= (new-expression 4) (solve-for
;               equation
;               'x
;              ))))
         
;(deftest simplification
;  (is (= 4 (simplify (new-expression '(* 2 2)))))
;  (let [expr (new-expression '(* 2 x))]
;    (is (= expr (simplify expr))))
;  (is (= 8 (simplify (new-expression '(* 4 (/ 4 2))))))
;         )
;
;
;












;  (is (= 'x
;         (simplify
;           '(* 2 (/ x 2))
;      )))
;  (is (= 'x
;         (simplify
;           '(/ (* 2 x) 2)
;      )))
;
;  (is (= '(/ x 2)
;         (simplify
;           '(* 2 (/ x 4))
;      )))
;  (is (= '(* x 2)
;         (simplify
;           '(/ (* x 4) 2)
;      )))
;  (is (= 3 (solve-equation
;             'x
;             '(= (* 3 x) 9)
;            )))
;;  (is (= 3 (solve-equation
;;             'x
;;             '(= (* x 3) 9)
;;            )))
 ;)
;
;
; ; (is (= 'x (simplify '(* 1 x))))
; (is (= '(/ x 1) (simplify-multiplicative-identity '(/ x 1))))
; (is (= '(* x 4) (simplify-multiplicative-identity '(* x 4))))
; (is (= 'x (simplify-multiplicative-identity '(* 1 x))))
;
; (is (= 22 (simplify-division-identity 22)))
; (is (= 'x (simplify-division-identity '(/ x 1))))
;
; (is (= 'x (simplify '(/ x 1))))
; (is (= 'x (simplify '(* x 1))))
; (is (= '(1 2 3) (simplify '(1 2 3))))
;         )
