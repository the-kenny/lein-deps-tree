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

## License

Copyright © 2012 Moritz Ulrich

Distributed under the Eclipse Public License, the same as Clojure.
