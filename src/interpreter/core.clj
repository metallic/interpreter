(ns interpreter.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn -main [& args]
  (if (nth args 0)
    (gl-interpreter (nth args 0))
    (println "Please specify a filename as the first argument")))

(defn gl-interpreter [program-code] (println program-code))
