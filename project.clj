(defproject report-gen "0.1.0-SNAPSHOT"
  :description "Generic Report Generator from a SQL DB and Dispatcher"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.9.0"]

                 [org.clojure/tools.logging "0.4.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [ch.qos.logback/logback-core "1.2.3"]

                 [org.clojure/tools.cli "0.3.5"]

                 [org.clojure/java.jdbc "0.7.5"]
                                        ; db classes
                 [com.microsoft.sqlserver/mssql-jdbc "6.2.2.jre8"]
                 [org.postgresql/postgresql "42.2.2"]]
  :main ^:skip-aot report-gen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
