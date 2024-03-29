# U-Fund: Animal Shelter/Humane Society
This project is a U-Fund website that is to be used by a local animal shelter/humane society. This website provides tools for a welcoming and supportive community with the purpose of helping homeless animals.  
  
An online U-Fund system built in Java 17, Angular 16.2.3, Springframework 2.6.2, and Maven 3.0.
  
## Team

- Chase Balmer
- Tszfai Choy
- Marisa Ortiz
- Tyler Combs
- Sarah Payne

# Sprint 4 Release
This sprint was essentially just design doc updates, but we did make a very small change to the adoptable animal class to add a hashcode method. This was done because without it our static code analysis was failing. 

## Prerequisites

- Java 11=>17 
- Maven 3
- Npm
- Node.js
- SpringFramework 2


## How to run it

1. Clone the repository and go to the root directory.  

Either:  
2. Execute '.\exec.bat'  
Or:  
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
2. Obtain the IP address of the target machine running the app.
3. Execute the predefined test cases outlined in the Acceptance Test plan.
4. Analyze the application's behavior and performance during test execution.
5. Document and report any deviations from expected outcomes, providing detailed information on encountered issues, if any.


## Design Principles 
**Sprint 1 Champion Principles**

*Single Responsibility* 
- During Sprint 1 our team focused on keeping our classes single responsibility. This means keeping classes as small as possible ad ensuring that every class or object we create should only do one thing.
- Our cupboardController is a good implementation of a single responsibility class; its only job is manipulating the needs in the cupboard. It does not hold the logic for doing these things, its only responsibility is to call the correct methods to make the manipulations happen.
- When adding the enchancements during Sprint 3, we added two more controller classes. The adoptionCupboardController was made with the sole responsibilty of handling the adoptionCupboard and adding/removing adoptableAnimals from the cupboard. The communityBoardController was similar in that it was responsable for handling Posts made and added to the communityBoard.These controllers helped us keep our classes small and efficient

*Controllers*
- During Sprint 1 our team focused on the controllers design principle. Controllers handle user input/actions and interact with the models and views. They facilitate communication between the user interface and the underlying system components.
- The controller principle is applied in our design by separating the user interface from the business logic of the application.
- Our cupboardController is a good example of a controller class in our code; it handles adding/removing/edting needs in the cupboard
- Across Sprint 2 and 3, we continued to use controllers as nessesary to keep the logic of the application far from the user interface. For every major component, a controller was made to help organize and streamline code and logic.

**Sprint 2 OO Design Principles** 

*Information Expert*
-  During Sprint 2, our team focused on making our Need and Cupboard classes 'information experts'. These classes have methods that can be called to find the states of their many properties. 
- The Cupboard class has many methods that support searching functionality, so that our cupboardController class can call cupboard methods with search terms and only recieve back needs that fit the search, rather than requesting the full cupboard and having to search through it itself every time. 
- The Need class has many methods that describe the standing of the need in terms of quantity available and quantity funded. While they were not used during this sprint as we haven't yet completed our checkout basket functionality, these methods will be useful by alowing the Need object itself to state if it is avaiable to be checked out rather than another class having to deal with the logic of deciding it.

*Open/Closed*
- During Sprint 2 our team focused on making our Users Open/Closed, meaning that they are 'open for extension, but closed for modification'. 
- Our team wrote a User interface to be used when creating types of Users for our website. While at the moment our team has only implemented the User interface in the creation of the Helper class, in future sprints we see the possibility of including different types of users that will have different abilities and the User interface will be an ideal place to start in creating those new Users. 
- We continued using interfaces across the project, especially within the persistance tier. These DAO interfaces creating a jumping off point for creating classes that extended them and also prevented us from adding more than what was nessasary to these classes. It kept our code from getting bloated and difficult to read through.

**Sprint 3 OO Design Principles**

*Low Coupling*
- During Sprint 3, our team focused on how classes interacted with eachother and consistently questioned whether one class was holding too much responsability. We focused on low coupling as a way to ease production and maintainability of the code as well as making the code easier to understand.
- Because we all worked simultaneously and we all wrote code meant to interact with another person's, low coupling was of greater importance so that we could make the learning process and implementation of different code easier. Low coupling can easily be observed across all tiers, but specifically in the model tier where we seperated the communityBoard class from the Post class. This low coupling allowed for the communityBoard to act as a holder for the posts made by the Post class instead of having it do both which would cause confusion and difficulty when it came to properly implementing the code.

*Pure Fabrication*
- In our goal to improve our applications design, we chose to focus on Pure Fabrication as another prinicple in Sprint 3. Our team believed it to be important not only as a way to achieve further Low Coupling but as a way to simplify more complex systems in our application.
- In the persistance tier, we made interfaces to be implemented into classes in order to help outline everything the class needed to complete. While these DAO interfaces weren't directly tied to the domain, they served as helpers to help connect classes together 

## License
MIT License
See LICENSE for details.
