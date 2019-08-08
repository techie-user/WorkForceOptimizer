# workforce-optimization-app

## Requirements

Develop a Spring boot application(Solution) to calculate the optimized workforce for given input:

- array of rooms(int) for every structure
- cleaning capacity Junior Cleaner(int)
- cleaning capacity Senior Cleaner(int)

##### Conditions:
 Maximum structures in cleaning provider portfolio does not exceed 100
 None of the structures will have more than 100 rooms
  
## Design
This application is designed around spring classes
* Listen to end-point for input
* Validate the input
* Process Optimization logic
* Return results as JSON Object

## Optimization approach
Inequality form for given input, `Rooms <= (No_of_Senior * Senior_Capacity) + (No_of_Juniors * Junior_Capacity)`, with the conditions that all parameters are non-negative integers, and `No_of_Seniors > 0`, `No_Of_Juniors >= 0`. 
The goal is to minimise the values of `No_of_Senior` and `No_of_Juniors`.

## Required Software:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

```shell
Navigate to root folder of the project and execute the following command:
mvn spring-boot:run
```
This will listen to [local endpoint](http://localhost:8090/workforce).

## Test
For testing sample data:

**In:** {"rooms": [35,21,17,28],"senior": 10, "junior": 6}

use curl command,

curl --header "Content-Type: application/json" --request POST --data "{\"rooms\": [35,21,17,28],\"senior\": 10, \"junior\": 6}"  http://localhost:8090/workforce

**Out:** [{"rooms":35,"senior":3,"junior":1},{"rooms":21,"senior":1,"junior":2},{"rooms":17,"senior":2,"junior":0},{"rooms":28,"senior":1,"junior":3}]

Programmatic Testing is done using spring rest template.
Manual Testing is performed using swagger-ui.
[Swagger Link](http://localhost:8090/swagger-ui.html)

## Copyright
Feel free to use the code.