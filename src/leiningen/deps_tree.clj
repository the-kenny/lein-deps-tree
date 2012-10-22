(ns leiningen.deps-tree
  (:require [cemerick.pomegranate.aether :as aether]))

(defn- make-dependency-tree [project]
  (let [add-auth (or (try (require 'leiningen.core.classpath)
                          (resolve 'leiningen.core.classpath/add-auth)
                          (catch java.lang.RuntimeException _))
                     ;; 1.x doesn't have `add-auth'. We just
                     ;; use identity here, this means no
                     ;; auth-support in 1.x.
                     identity)]
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
       (print (str (apply str (repeat level \space))))
       (prn n)
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
