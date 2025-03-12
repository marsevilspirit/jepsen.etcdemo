(ns jepsen.etcdemo
  (:require [jepsen.cli :as cli]
            [jepsen.tests :as tests]))

(defn etcd-test
  "Given an options map from the command line runner (e.g. :nodes, :ssh, :concurrency, ...), constructs a test map."
  [opts]
  (merge tests/noop-test ; noop-test means none test to run
         {:pure-generators true}
         opts))

(defn -main
  "Handles command line arguments.
  Can either run a test, or a web server for browsing results."
  [& args]
  (cli/run! (merge (cli/single-test-cmd {:test-fn etcd-test}) ; main test
                   (cli/serve-cmd)) ; serve a web browser to explore the history of our test results
            args))
