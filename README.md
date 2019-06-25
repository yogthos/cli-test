# cli-test

An example Clojure CLI tool using GraalVM based on http://markwoodhall.com/26-06-2014-command-line-applications-in-clojure/

prerequisites:

* [JDK](https://openjdk.java.net/)
* [GraalVM](https://github.com/oracle/graal)
* [Leiningen](https://leiningen.org/)

Compiling with GraalVM:

    lein native-image

running

    target/app stop -p 4000