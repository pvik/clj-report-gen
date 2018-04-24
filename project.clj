(defproject report-gen "0.1.0-SNAPSHOT"
  :description "Generic Report Generator from a SQL DB and Dispatcher"
  :url "https://github.com/pvik/clj-report-gen"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 ;; logging
                 [org.clojure/tools.logging "0.4.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [ch.qos.logback/logback-core "1.2.3"]
                 [org.clojure/tools.cli "0.3.5"] ;; for command line args
                 [com.draines/postal "2.0.2"] ;; for email dispatch
                 [dk.ative/docjure "1.12.0"] ;; for xlsx spreadsheets
                 [org.clojure/java.jdbc "0.7.5"]
                 ;; db classes
                 [com.microsoft.sqlserver/mssql-jdbc "6.4.0.jre8"]
                 [org.postgresql/postgresql "42.2.2"]
                 ;; mvn install:install-file -Dfile=ojdbc6-12.1.0.1-atlassian-hosted.jar -DgroupId=local -DartifactId=ojdbc6 -Dversion=12.1.0.1 -Dpackaging=jar -DgeneratePom=true
                 [local/ojdbc6 "12.1.0.1"]]
  :main ^:skip-aot report-gen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
