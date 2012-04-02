(ns leiningen.deps-tree
  (:require [cemerick.pomegranate.aether :as aether]))

(try )

(defn- make-dependency-tree [project]
  (let [[add-auth two?] (or (try (require 'leiningen.core.classpath)
                                 [(resolve 'leiningen.core.classpath/add-auth)
                                  true]
                                 (catch java.io.FileNotFoundException _))
                            (try (require 'leiningen.classpath)
                                 [(resolve 'leiningen.classpath/add-auth)]
                                 (catch java.io.FileNotFoundException _)))]
    (aether/dependency-hierarchy
     (:dependencies project)
     (aether/resolve-dependencies
      :local-repo (:local-repo project)
      :offline? (:offline project)
      :repositories (add-auth (:repositories project))
      :coordinates (:dependencies project)
      :transfer-listener :stdout))))


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
  (if-let [indent (Integer/parseInt (or (first args) "4"))]
    (print-tree (make-dependency-tree project) indent)))
