(ns leiningen.deps-tree
  (:require [leiningen.core.classpath :as classpath]
            [cemerick.pomegranate.aether :as aether]))

(defn- make-dependency-tree [project]
  (aether/dependency-hierarchy
   (:dependencies project)
   (aether/resolve-dependencies
    :local-repo (:local-repo project)
    :offline? (:offline project)
    :repositories (classpath/add-auth (:repositories project))
    :coordinates (:dependencies project)
    :transfer-listener :stdout)))


;;; TODO: Get rid of this recursion
(defn- print-tree
  ([tree level increment]
     (doseq [[n c] tree]
       (println (str (apply str (repeat level \space))) n)
       (when c
         (print-tree c (+ level increment) increment))))
  ([tree increment]
     (print-tree tree 0 increment))
  ([tree]
     (print-tree tree 5)))

(defn deps-tree
  "Prints a nicely formatted tree of all dependencies"
  [project & args]
  (if-let [indent (Integer/parseInt (first args))]
    (print-tree (make-dependency-tree project) indent)
    (print-tree (make-dependency-tree project) 4)))
