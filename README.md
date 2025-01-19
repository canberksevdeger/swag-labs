# swag-labs #
Interview Exercise - Java / Selenium Project with Cucumber
### Requirements ###

* Java 21+
* Maven 3

## Usage/Examples ##
### How to Run tests ###
```shell
    mvn -B clean test
```
### How to generate allure reports ###
```shell
    mvn allure:report
```
## Report-Result and Log directories ##
* Allure
```
    results: target/allure-results
    reports: target/allure-report
```
* Log4J
```
  log file: target/cucumber-logs
```