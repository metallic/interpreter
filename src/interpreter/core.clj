(ns interpreter.core
   (:require [instaparse.core :as insta]
             [quil.core :as q] ))            
  
 (def syntax-analysis 
   "Syntax-analysis enforces logic for language (first command is always DIMENTIONS command) and performs syntax check." 
   (insta/parser 
     "<program_code> = command_dim other_commands* 
      <other_commands> = command_line | command_pixel | command_rec | command_set | command_cir 
      command_line = 'LINE' <whitespace> number <whitespace> number <whitespace> number <whitespace> number <semicolon> 
      command_dim = 'DIMENSIONS' <whitespace> number <whitespace> number <semicolon> 
      command_pixel = 'PIXEL' <whitespace> number <whitespace> number <semicolon> 
      command_rec = 'RECTANGLE' <whitespace> number <whitespace> number <whitespace> number <whitespace> number <semicolon> 
      command_set = 'SET_COLOR' <whitespace> number <whitespace> number <whitespace> number <semicolon> 
      command_cir = 'CIRCLE' <whitespace> number <whitespace> number <whitespace> number <semicolon> 
      <semicolon> = <';'>(<whitespace>)* 
      <whitespace> = #'\\s+' 
      number = #'[0-9]+'")) 
 
 (defn get-operator [[_ op[][]]] op)
 
 (defn get-operator-arg-1 [[_ _[_ x][]]]  ( if (nil? x) (int 0) (Integer/parseInt x) ) )
 
 (defn get-operator-arg-2 [[_ _[_ _][_ y]]] ( if (nil? y) (int 0) (Integer/parseInt y) ) )
 
 (defn get-operator-arg-3 [[_ _[_ _][_ _][_ z]]] ( if (nil? z) (int 0) (Integer/parseInt z) ) )
 
 (defn get-operator-arg-4 [[_ _[_ _][_ _][_ _][_ q]]] ( if (nil? q) (int 0) (Integer/parseInt q) ) )
 
 (defn process-instructions-2 [list]
   (let [command_first (first list)]
     
     (if (seq command_first)
       ( let [command_rest (rest list)
              operator (get-operator command_first)
              x (get-operator-arg-1 command_first)
              y (get-operator-arg-2 command_first)
              z (get-operator-arg-3 command_first)
              q (get-operator-arg-4 command_first)]
         
         (condp = (get (first list) 1)
           
           "SET_COLOR" (q/stroke x y z)
                         
           "RECTANGLE" ( q/rect x y z q)
                        
           "CIRCLE" ( q/ellipse x y z z)
                       
           "LINE" ( q/line x y z q)
                        
           "PIXEL" ( q/line x y x y)
                         
           "DIMENSIONS" ()
           ) ;CONDP
         (process-instructions-2 command_rest)
         );LET2
       ) ;IF
     ) ;LET1
   )
 
 (defn setup []
  (q/color-mode :rgb)
  (q/background 10)
  (q/no-fill)
  (q/stroke-weight 5) 
  (process-instructions-2 program-code)
  )  
 
 (defn -main [& args] 
   (if (nth args 0) 
     (do 
       
       (def program-code (syntax-analysis (slurp (nth args 0))))
 
       (q/defsketch my-drawing
         :title "Drawing"
         :size [(get-operator-arg-1 (first program-code)) 
                (get-operator-arg-2 (first program-code))
                ]
         :setup setup
         )
       )  
     (println "Please specify a filename as the first argument")))