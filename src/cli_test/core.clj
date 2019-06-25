(ns cli-test.core
  (:require
   [clojure.string :as string]
   [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def cli-options
  [["-p" "--port PORT" "The Port number"
    :default 8080
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Should be a number between 0 and 65536"]]
   ["-h" "--help"]])

(defn help [options]
  (->> ["cli-test is a command line tool for starting and stopping a HTTP server"
        ""
        "Usage: cli-test [options] action"
        ""
        "Options:"
        options
        ""
        "Actions:"
        "  start      Start a HTTP server"
        "  stop       Stop a HTTP server"]
       (string/join \newline)))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn error-msg [errors]
  (str "There were errors processing the command line arguments\n\n"
       (string/join \newline errors)))

(defmulti execute (fn [[id] _] (keyword id)))

(defmethod execute :start [args options]
  (println "starting " options))

(defmethod execute :stop [args options]
  (println "stopping" options))

(defmethod execute :default [args options]
  (exit 1 (str "no handler found for: " args)))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 (help summary))
      (not= (count arguments) 1) (exit 1 (help summary))
      errors (exit 1 (error-msg errors)))
    (execute arguments options)))