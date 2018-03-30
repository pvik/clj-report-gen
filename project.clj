(defproject report-gen "0.1.0-SNAPSHOT"
  :description "Generic Report Generator from a SQL DB and Dispatcher"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 ;; logging
                 [org.clojure/tools.logging "0.4.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [ch.qos.logback/logback-core "1.2.3"]
                 [org.clojure/tools.cli "0.3.5"] ;; for command line args
                 [dk.ative/docjure "1.12.0"] ;; for xlsx spreadsheets
                 [org.clojure/java.jdbc "0.7.5"]
                 ;; db classes
                 [com.microsoft.sqlserver/mssql-jdbc "6.2.2.jre8"]
                 [org.postgresql/postgresql "42.2.2"]
                 [com.oracle/ojdbc6 "12.1.0.1-atlassian-hosted"]
                 [oracle/ojdbc6 "11.2.0.3"]
                 ;; [com.oracle/ojdbc14 "10.2.0.4.0"]
                 ]
  :main ^:skip-aot report-gen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
