(defproject jepsen.etcdemo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :main jepsen.etcdemo
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [jepsen "0.2.1-SNAPSHOT"]
                 [verschlimmbesserung "0.1.3"]
                 [slingshot "0.12.2"]]
  :repl-options {:init-ns jepsen.etcdemo})
