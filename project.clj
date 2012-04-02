(defproject lein-deps-tree "0.1.0"
  :description "Prints all dependencies formatted as a tree"
  :url "https://github.com/the-kenny/lein-deps-tree"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [aleph "0.2.1-alpha2-SNAPSHOT"]
                 [com.cemerick/pomegranate "0.0.9"
                  :exclusions [org.slf4j/slf4j-api]]]
  :eval-in-leiningen true)
