(defproject cli-test "0.1.0"
  :description "example CLI app usnig Graal"
  :url "https://github.com/yogthos/cli-test"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.4.2"]]
  :plugins [[io.taylorwood/lein-native-image "0.3.0"]]            
  :repl-options {:init-ns cli-test.core}
  :main cli-test.core
  :aot :all
  :native-image {:name     "app"
                  :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                  :opts     ["--report-unsupported-elements-at-runtime"
                            "--initialize-at-build-time"
                            "--allow-incomplete-classpath"
                            ;;avoid spawning build server
                            "--no-server"]})
