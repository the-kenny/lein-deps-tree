# lein-deps-tree

A Leiningen plugin to print a print a nicely formatted tree of a project's dependencies.

## Usage

Put `[lein-deps-tree "0.1.0]` into the `:plugins` vector of your `:user` profile, or if you are on Leiningen 1.x do `lein plugin install lein-deps-tree 0.1.0`.

Run

    $ lein deps-tree

to print the tree of dependencies.

Run

    $ lein deps-tree <integer>

to control the indentation level.

## Example Output

Example output of a project with `[compojure "1.0.1"]` and `[clojure "1.3.0"]` as dependency:

    $ lein deps-tree <integer>
    [compojure 1.0.1]
             [clout 1.0.1]
             [org.clojure/core.incubator 0.1.0]
             [org.clojure/tools.macro 0.1.0]
             [ring/ring-core 1.0.1]
                      [commons-codec 1.4]
                      [commons-fileupload 1.2.1]
                      [commons-io 1.4]
                      [javax.servlet/servlet-api 2.5]
    [org.clojure/clojure 1.3.0]

## License

Copyright © 2012 Moritz Ulrich

Distributed under the Eclipse Public License, the same as Clojure.
