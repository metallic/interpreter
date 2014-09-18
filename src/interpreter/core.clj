(ns interpreter.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [instaparse.core :as insta]))

(defn -main [& args]
  (if (nth args 0)
    (syntax-analysis (slurp (nth args 0)))
    (println "Please specify a filename as the first argument")))

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