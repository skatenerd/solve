(ns solve.core-test
  (:use clojure.test
        solve.core))

(deftest data-structures
   (let [expression (new-expression '(+ 1 2))]
     (is (= '+ (operator expression)))
     (is (= '(1 2) (arguments expression)))
     )

   (let [unary (new-expression 2)]
     (is (= 'identity (operator unary)))
     (is (= '(2) (arguments unary))))

   (let [complex (new-expression '(+ (* 4 3) 2))]
     (is (= '+ (operator complex)))
     (is (= 2 (last (arguments complex))))
     (let [first-argument (first (arguments complex))]
       (is (= (new-expression '(* 4 3)) first-argument))
       (is (= '* (operator first-argument)))
       (is (= '(4 3) (arguments first-argument)))
       )
   )

   (is (opposite-operators? '+ '-))
   (is (opposite-operators? '- '+))
   (is (opposite-operators? '* '/))
   (is (not (opposite-operators? '* '+)))
   )

(deftest one-variable
  (let [equation (new-equation 'x 4)]
    (is (= (new-expression 4) (solve-for
               equation
               'x
              ))))
         ;goal
;  (let [equation (new-equation '(* 4 x) 16)]
;    (is (= (new-expression 4) (solve-for
;               equation
;               'x
;              ))))
         
         )
(deftest simplification
  (is (= 4 (simplify (new-expression '(* 2 2)))))
  (let [expr (new-expression '(* 2 x))]
    (is (= expr (simplify expr))))
  (is (= 8 (simplify (new-expression '(* 4 (/ 4 2))))))
         )
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
