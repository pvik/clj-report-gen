(defproject report-gen "0.1.0-SNAPSHOT"
  :description "Generic Report Generator and Dispatcher"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.4.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [ch.qos.logback/logback-core "1.2.3"]
                 [propertea "1.2.3"]
                 [org.clojure/java.jdbc "0.7.5"]
                 [com.microsoft.sqlserver/mssql-jdbc "6.2.2.jre8"]]
  :main ^:skip-aot report-gen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
