# report-gen

An simple application that runs a query and distributes the results via email, ftp, etc.

## Usage

Create your report details into an edn file. (Examples are below).

    $ java -jar report-gen-0.1.0-standalone.jar -d [data-dir] -r [report-name]

## Options

```
 Switches               Default              Desc
 --------               -------              ----
 -h, --no-help, --help  false                Print this help
 -d, --data-dir         resources            data directory to find report and db .edn files
 -o, --output-dir       /tmp                 directory to store generated reports
 -e, --email-server     resources/email.edn  email server properties
 -r, --report                                Report .edn file
```

Sample database and report .edn files are in the resources folder.

The `--data-dir` folder should have `reports` and `databases` folders. Databses defined under the `databases` folder can be referenced in the report's `.edn` file.

## Examples

```
;; ~/report-data/reports/test-report.edn
{:name  "test-report"
 :db    "mssql"
 :query "SELECT * FROM NOTES
WHERE CREATION_DATE > CONVERT(DATE, DATEADD(DAY, -7, GETDATE()))"
 :output :xlsx
 :dispatch [
   {:type :email
    :from    "report.generator@mycompany.com"
    :to      ["user1@mycompany.com" "user2@mycompany.com"]
    :subject "Test Report"
    :body    "Please find the requested report attached."}]
}
```

```
;; ~/report-data/databases/mssql.edn
{:dbtype     "mssql"
 :dbname     "DEMO"
 :host       "db1.data.mycomany.local"
 :user       "dbuser"
 :password   "s3cret"}
```

You can now run `test-report` as

```
java -jar report-gen-0.1.0-standalone.jar -d ~/report-data -e ~/report-data/email.edn -r test-report
```

## Caveats

### Oracle JDBC Driver

```
curl -O https://maven.atlassian.com/3rdparty/com/oracle/ojdbc6/12.1.0.1-atlassian-hosted/ojdbc6-12.1.0.1-atlassian-hosted.jar

mvn install:install-file -Dfile=ojdbc6-12.1.0.1-atlassian-hosted.jar -DgroupId=local -DartifactId=ojdbc6 -Dversion=12.1.0.1 -Dpackaging=jar -DgeneratePom=true
```

## License

Copyright © 2018

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
