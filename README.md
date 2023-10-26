# U-Fund: Animal Shelter/Humane Society
# Modify this document to expand any and all sections that are applicable for a better understanding from your users/testers/collaborators (remove this comment and other instructions areas for your FINAL release)

An online U-Fund system built in Java 17=> and ___ _replace with other platform requirements_ ___
  
## Team

- Chase Balmer
- Tszfai Choy
- Marisa Ortiz
- Tyler Combs
- Sarah Payne

# Sprint 2 Release
Our demo did not include this -> now when a helper searches for a need, they may add a need to their funding basket from the search list that appears.
Known bugs/disclaimers:
We are currently only permitting predefined helpers to have proper usage of the program.
To login as a helper, use the login 'helperOne'

Not really a bug, but a note that needs can be added as many times as you want into a basket, once checkout is implemented this will change. 
Needs also do not become invisible to other helpers when added to a basket as without checkout functionality (which was not required), there is no need to prevent other helpers from adding them. 

## Prerequisites

- Java 11=>17 (Make sure to have correct JAVA_HOME setup in your environment)
- Maven
-  _add any other tech stack requirements_


## How to run it

1. Clone the repository and go to the root directory.
2. Execute `mvn compile exec:java`
3. Open in your browser `http://localhost:8080/` to view the sprint server
4. In another powershell, from the root directory, run ng serve
5. Open in your browser `http://localhost:4200/` to view the actual page
6. Login and use site as you please




## How to test it

The Maven build script provides hooks for run unit tests and generate code coverage
reports in HTML.

To run tests on all tiers together do this:

1. Execute `mvn clean test jacoco:report`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/index.html`

To run tests on a single tier do this:

1. Execute `mvn clean test-compile surefire:test@tier jacoco:report@tier` where `tier` is one of `controller`, `model`, `persistence`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/{controller, model, persistence}/index.html`

To run tests on all the tiers in isolation do this:

1. Execute `mvn exec:exec@tests-and-coverage`
2. To view the Controller tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
3. To view the Model tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
4. To view the Persistence tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`

*(Consider using `mvn clean verify` to attest you have reached the target threshold for coverage)
  
  
## How to generate the Design documentation PDF

1. Access the `PROJECT_DOCS_HOME/` directory
2. Execute `mvn exec:exec@docs`
3. The generated PDF will be in `PROJECT_DOCS_HOME/` directory


## How to setup/run/test program 
1. Tester, first obtain the Acceptance Test plan
2. IP address of target machine running the app
3. Execute ________
4. ...
5. ...

## Design Principles 
We are championing the principles of single responsibility and controllers. 
The controller principle is applied in our design by separating the user interface from the business logic of the application. Controllers handle user input/actions and interact with the models and views. They facilitate communication between the user interface and the underlying system components. An example of this in our system is our cupboardController, the took handling adding/removing/edting needs in the cupboard. 
The single responsibility principle is implemented in our design through the means of keeping classes as small as possible. Every class or object we create should only do one thing, it should only have one responsibility. An example of this could be our cupboard controller, its only job is manipulating the needs in the cupboard. It does not hold the logic for doing these things, its only responsibility is to call the correct methods to make the manipulations happen.
Other principles will be updated at the same time with out design doc, To be updated 10/26 my midnight

## License
MIT License
See LICENSE for details.
