# Cucumber Test Automation 

This repository contains Cucumber Test Automation written in Java + Cucumber + Maven

## Prerequisites

You will need the following things properly installed on your computer.

* make sure git is installed in your system
* clone https://github.com/alishahzad89/CucumberAutomationHSBC.git
* make sure you have ide like intellij or eclipse

## Setting up

* Import as maven project to ide from cloned repository
* wait for Maven to download all external dependency

### Running tests

- Run all

       Open test TestRunner.java and remove tags from CucumberOptions
       Select run as jUnit configuration
       All report will be shown on console ouput
- Run test cases with specific tags

       Open test TestRunner.java and add tags to CucumberOptions like tags = "@Positive or @Negative"
       Select run as jUnit configuration
       All report will be shown on console ouput


### Output

Test outputs can be seen on console and junit run window

### HTML Report

After test execution please open file "report/cucumber/cucumber-html-reports/overview-features.html"

### Shortcomings/TODOs:

- Do not have information for timezone, hence taken "UTC" into consideration, it is causing failure of few test scenarios.
- We can have more validation for exact rates values, expected values can be stored in json and actual values can be taken from response.

### Author
Shahzad Ali
QA Automation Expert
