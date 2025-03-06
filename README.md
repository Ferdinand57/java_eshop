## Module 1 Reflection


When i implement all the requested feature, i make sure to use descriptive name to all of the functions name. I also make sure to limit function to a small size and make sure they only do as little thing as they can. To make sure my code easy to read, i also make sure to use consistent formatting on all my files. To make sure my function is secure, i also use POST for modifying data like delete and edit feature instead of GET. And to make my code more secure i also added several error handling, such as making sure when the delete button is pressed without any of the checkboxes is check, it will do nothing.

## Module 2 Reflection

My strategy was pretty straightforward, I went through the PMD reports, and I addressed the issues one by one, starting with the easiest ones. I tried to understand what each rule meant and then made the necessary changes to the code to fix the violation. I made sure to commit each fix separately, as per the exercise rule.

I think the current implementation is a good start for CI/CD, but it's not fully there yet. The CI part is pretty solid, since the workflow automatically runs tests on every push to the main branch. However, the deployment part is still manual, since I have to manually merge the code to the main branch. To achieve true Continuous Deployment, the workflow should automatically deploy the code to Koyeb after the tests pass.

## Module 3 Reflection

### 1. Explain what principles you apply to your project!


- SRP (Single Responsibility Principle): As the name suggest, SRP require that each class has one job. I have applied this principle by separating the web controller, the logic, and the storage in different classes. For example, in my code i made sure that ProductController and CarController only handles the web action, such as requests and alternating between pages. The ProductService and CarService only handles the logic/internal mechanism, such as creating them, finding them, updating them, and deleting them. Lastly the ProductRepository and CarRepository only there to stores the existing object, like a storage device. Because of this principle, i can change one aspect of the code, and it won't mess up other part of the code, because the change will be isolated, relatively. 
    

- OCP (Open/Closed Principle): By following OCP, i allow my code to be open into adding new stuff without changing the old stuff, which makes my old stuff basically isolated from new add ons, or in other word, closed. For example because in this project the controller, the logic definition, and the logic implementation is in a different files, ProductController, ProductService, and ProductServiceImpl respectively, i can for example, create a brand new implementation to the function defined in ProductService with a new ProductServiceImpl2, and it wouldn't require me to modify the ProductController.


- LSP (Liskov Substitution Principle): LSP dictate that object of a superclass should be replaceable with objects of it's subclasses without affecting the correctness of the program. Basically if a code work's on a superclass, is should also works on an object that inherit from that superclass. Because inheritance is not used in this project, LSP is satisfied.


- ISP (Interface Segregation Principle): This principle suggest that if an interface is too large, it should be broken down into smaller but more spesific interfaces. Because my current project interfaces like CarService and ProductService is not too large, relatively speaking, i do not see the need to break them down into smaller, more spesific interfaces.


- DIP (Dependency Inversion Principle): DIP states that high level module shouldn't depend on low level module. Both should instead depend on abstractions, and the abstractions shouldn't depend on the details. In my code the high level level module, which is ProductController, does not depend on the low level module, in this case is the ProductRepository which it periodically "accessed", instead to connect both of them i'm using an abstraction, which in this case is ProductService, which in it of itself doesn't depend on the details which is ProductServiceImpl, because as discussed before, my code already follows the Open/Closed Principle.

### 2. Explain the advantages of applying SOLID principles to your project with examples.


- Because of the Single Responsibility Principle (SRP), my code is easier to read and understand, mainly because each process of controller, interface, interface implementation is separated.


- Because of principles that isolated parts of the code, like SRP, OCP, and maybe DIP, if i wanted to change one thing, i can just change it's spesific code, without needing to change other part of the code because of how little they depend on eachother.


- Because each class has one job, it's super easy to write tests. For example, i can test the ProductController all by itself, the ProductService all by itself, and the ProductRepository all by itself, which means I can find bugs and make sure everything works easier.


- Not only can i change things, because of OCP, i can safely add new features without changing the existing code, for example, if i want to add new kind of product, i can just create a new service implementation and repository, and it "should" work without breaking anything else

### 3. Explain the disadvantages of not applying SOLID principles to your project with examples.


- Without SRP, everything would be crammed into a single giant class, imagine the ProductController also handling the database stuff and the business logic, it would be impossible to understand or maintain.


- Without the OCP, if I changed how products are stored, i have to change the controller, the service, and everything else, one small change could break the whole system.


- Testing Would Be a Nightmare, how do you test a class that does everything? you don't

## Module 4 Reflection

### 1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

#### Evaluate Your Tests: Correctness

- Do I have enough functional tests to reassure myself that my application really works, from the point of view of the user?

Not really, the test I implemented are enough to cover the basic function of my code, but it doesn't cover all of it. The test I have implemented is enough to reassure me that the code will work properly, but not enough to reassure me it will do that in every condition.

- Am I testing all the edge cases thoroughly?

No, as I have said before, the test I have implemented is enough for the basic mechanic of the program, but I'm sure there's many edge cases that I don't know about

- Do I have tests that check whether all my components fit together properly? Could some integrated tests do this, or are functional tests enough?

No, I currently don't have test that integrate with the program. Currently with mockito, my test only mock the verification. Currently, I don't know if functional tests are enough.

#### Evaluate Your Tests: Maintainability

- Are my tests giving me the confidence to refactor my code, fearlessly and frequently?

Currently, my tests are giving me some confidence to refactor my code, but that's probably because right now my code is simple enough that I can find part of the code that can be refactored easily.

- Are my tests helping me to drive out a good design? If I have a lot of integration tests but less unit tests, do I need to make more unit tests to get better feedback on my code design?

Yes, by using TDD, I have managed to use my tests to help me make a better implementation of the code. By writing the requirement first in the form of the test, I can easily make the implementation of the requirements. I don't think i have too few unit tests, but i can see the lack of integration tests.

#### Evaluate Your Tests: Productive Workflow

- Are my feedback cycles as fast as I would like them? When do I get warned about bugs, and is there any practical way to make that happen sooner?

The feedback cycles from the tests are currently really fast, i get warned about bugs early, and i don't think there is any practical way i can make it faster than it is.

- Is there some way that I could write faster integration tests that would get me feedback quicker?

yes, the easiest way to make faster integration tests that give feedback quicker, is to set up the minimum database needed for each test, this way the integration test doesn't have to overwork itself unnecessarily

- Can I run a subset of the full test suite when I need to?

yes, currently, i can run a subset of the full test suite when i need to using IntelliJ built-in feature to test individual tests by right-clicking the test name inside the test suite.

- Am I spending too much time waiting for tests to run, and thus less time in a productive flow state?

No, currently, where i'm running these tests, i get back feedback from it very quickly therefore doesn't waste a lot of time not in a productive flow state.

### 2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

- Fast: The tests run as fast as possible so it can be run without interrupting your workflow.

yes, currently no unit tests involve slow operation like accessing the database. Therefore, the tests is quite fast

- Isolated/Independent: A test case must not interfere, change the state of functions, or dependent to other test cases.

In the OrderTest suite, each test method creates its own Order objects, which means there's no shared state between tests, effectively isolating each tests.

In the OrderRepositoryTest suite, the setUp method creates a fresh OrderRepository and a new list of Order objects for each test, which makes the tests isn't dependent on the side effects of previous tests, which effectively isolating each tests.

In the OrderServiceImplTest suite, by using Mockito to mock the OrderRepository, i isonlates the OrderService logic and prevents tests from depending on the real repository implementation, which isolate it.

- Repeatable: Tests must be able to run repeatedly, with consistent result.

Yes, the currently implemented tests is very much repeatable with consistent repeated results.

- Self-Validating: Tests must be self-validating (have strict and correct assertions to pass/fail).

Yes, as far as i know, all of my unit tests are using JUnit assertion to self-validate

- Thorough/Timely: Tests must cover all happy paths and unhappy paths. Cover all possibility of results and errors. Tests must be designed before coding.

Yes, my tests did cover all happy paths and unhappy paths that's described in the tutorial section, which cover all possibility of results and errors, which is designed before coding.