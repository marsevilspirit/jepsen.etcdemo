(ns jepsen.etcdemo
  (:require [clojure.tools.logging :as log]
            [clojure.string :as str]
            [jepsen [cli :as cli]
             [control :as c]
             [db :as db]
             [tests :as tests]]
            [jepsen.control.util :as cu]
            [jepsen.os.debian :as debian]))

(defn db
  "Etcd DB for a particular version."
  [version]
  (reify db/DB ; the db function uses reify to construct a new object satisfying jepsen's DB protocol (from the db namespace).
    ; setup! and teardown! are the two methods that must be implemented.
    (setup! [_ test node]
      (log/info node "installing etcd" version))
    (teardown! [_ test node]
      (log/info node "tearing down etcd"))))

(defn etcd-test
  "Given an options map from the command line runner (e.g. :nodes, :ssh, :concurrency, ...), constructs a test map."
  [opts]
  (merge tests/noop-test ; noop-test means none test to run
         opts
         {:name "etcd"
          :os debian/os
          :db (db "v3.1.5")
          :pure-generators true}))

(defn -main
  "Handles command line arguments.
  Can either run a test, or a web server for browsing results."
  [& args]
  (cli/run! (merge (cli/single-test-cmd {:test-fn etcd-test}) ; main test
                   (cli/serve-cmd)) ; serve a web browser to explore the history of our test results
            args))
