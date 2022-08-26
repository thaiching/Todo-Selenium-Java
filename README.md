This repository contains a basic framework to test the TodoMVC application (https://todomvc.com/examples/vue)
which allows users to add, edit, delete, complete and filter active/completed todo lists.

This behavioral driven development automation framework uses Selenium, cucumber-java, cucumber-junit, 
Page Objects Patterns and Maven as build tool.

Installation (pre-requisites)

# Setting up Selenium Java project
1. Install Java and set path
2. Install maven and set path
3. For simplicity, this project by default allows the tests to run locally with Chrome. Please install ChromeDriver, and add its location to your system PATH.
4. Clone repository 
6. To install all dependencies, run
   $ mvn clean install

# Running Test
By default, the tests will run on Chrome browser. 
- Go to project root directory
- Use "mvn test" command to run the test. 

# Reporters
After the completion of executing the tests, 2 types of reports will be generated (HTML and JSON reports).
Both the HTML and JSON reports will be generated under the folder /target/reports respectively
