## Project Name

A brief description of your project, its purpose, and its functionality.

## Table of Contents

1. Features
2. Getting Started
3. Prerequisites
4. Installation
5. Running Tests



## Features

User login functionality with valid and invalid scenarios.
Checks for successful login, locked accounts, and missing credentials.
Integration with TestNG for automated testing.


## Getting Started

These instructions will help you set up the project locally for development and testing.

## Prerequisites
Java Development Kit (JDK) 8 or higher
Apache Maven (if using Maven for dependencies)
TestNG framework


## Installation
Clone the repository:
** git clone https://github.com/yourusername/yourproject.git


** cd yourproject

** mvn install

## Running Tests
mvn test

## Running Tests with Extent Reports

To run the tests and generate Extent Reports, you can use the following command:

mvn test

You have two options for running the tests:

### 1. Running Tests Directly from the `login.feature` File

If you are using a Cucumber framework, you can run the tests directly from the `login.feature` file. Make sure you have Cucumber configured properly in your project.

### 2. Running Tests from the `LoginRunner` Class

If you prefer to run tests using TestNG, you can execute the tests directly from the `LoginRunner` class. Use the following command to run all the tests in the class:


mvn test -Dtest=LoginRunner


### To run the tests and generate Extent Reports, you can use the following command:

mvn test


###  Accessing Javadoc

Javadoc is generated for the project and can be found in the following directory:


-- To view the Javadoc, open the `index.html` file in your web browser. You can generate the Javadoc using the command:


```bash
-- mvn javadoc:javadoc

-- open index.html
