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
# Improvements with granted 10/26 midnight release
The team has updated the logic so that any helper can now login. Any username other than admin will be a helper and their funding basket will be saved and unique to them. 
The team has updated cupboard/basket logic so that now when a need is added to a basket, the quantity of that need in the cupboard is reduced by one. A helper can add a need multiple times into their cupboard (ex: fund 5 dog food), but will not be able to add the need more times than the cupboard is offering. When a need has been fully placed in baskets, no helpers will be able to add need to their basket(ex: cat food has 5 quantity, once 5 of this need is put in baskets, it can no longer be added to a basket). These changes are reflected across all users.
Managers no longer see or edit a needs ID

Known Bugs/Disclaimers 
Checkout is not implemented - team was under impression it was not required for this sprint. 
When needs are either deleted or modified, they will still remain in helpers baskets without those changes reflected. The team made this decision as they felt in the future when a user selects to checkout, that is when these changes would be reflected and the helper would be notified. 
In this case though, it is still possible for a helper to delete the need from their basket.
If a need is searched for and then added to basket, followed by removing the search query, the need quantity in the cupboard is not reflected until user signs in and out. This could allow someone to add extra amounts of a need if used in this fashion.

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
**Sprint 1 Champion Principles**

*Single Responsibility* 
- During Sprint 1 our team focused on keeping our classes single responsibility. This means keeping classes as small as possible ad ensuring that every class or object we create should only do one thing.
- Our cupboardController is a good implementation of a single responsibility class; its only job is manipulating the needs in the cupboard. It does not hold the logic for doing these things, its only responsibility is to call the correct methods to make the manipulations happen.

*Controllers*
- During Sprint 1 our team focused on the controllers design principle. Controllers handle user input/actions and interact with the models and views. They facilitate communication between the user interface and the underlying system components.
- The controller principle is applied in our design by separating the user interface from the business logic of the application.
- Our cupboardController is a good example of a controller class in our code; it handles adding/removing/edting needs in the cupboard

**Sprint 2 OO Design Principles** 

*Information Expert*
-  During Sprint 2, our team focused on making our Need and Cupboard classes 'information experts'. These classes have methods that can be called to find the states of their many properties. 
- The Cupboard class has many methods that support searching functionality, so that our cupboardController class can call cupboard methods with search terms and only recieve back needs that fit the search, rather than requesting the full cupboard and having to search through it itself every time. 
- The Need class has many methods that describe the standing of the need in terms of quantity available and quantity funded. While they were not used during this sprint as we haven't yet completed our checkout basket functionality, these methods will be useful by alowing the Need object itself to state if it is avaiable to be checked out rather than another class having to deal with the logic of deciding it.

*Open/Closed*
- During Sprint 2 our team focused on making our Users Open/Closed, meaning that they are 'open for extension, but closed for modification'. 
- Our team wrote a User interface to be used when creating types of Users for our website. While at the moment our team has only implemented the User interface in the creation of the Helper class, in futire sprints we see the possibility of including different types of users that will have different abilities and the User interface will be an ideal place to start in creating those new Users. 

## License
MIT License
See LICENSE for details.
