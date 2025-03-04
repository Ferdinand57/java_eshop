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