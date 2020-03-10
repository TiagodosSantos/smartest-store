#  Spring Boot Project 

This project intends to create an API store.

## Features
* Expose API
* Consume API
* Test API
* UI to test API (Swagger)

##  Getting Started

All Maven plugins and dependencies are available from [Maven Central](https://search.maven.org/). Please have installed
* JDK 1.8 (tested with both Oracle and OpenJDK)
* Maven 3




## Running the full application


You can access the application running on AWS Server (See "Accessing the full application").

Alternatively, you can run the application by executing the jar located in the main folder smartest-store as follows:

```bash
$ java -jar smartest-store/smartest-store.jar
```


The application will be accessible at the following address: 

#### Swagger UI: 
http://localhost:8080/swagger-ui.html.

#### Default (Use swagger for a better experience) 
http://localhost:8080/invoice.

http://localhost:8080/customer.

You can also recreate the package as a single artifact by running the `mvn install`. Three tests will be executed throughout this process. After that the application can be started using:

```bash
$ java -jar smartest-store/target/smartest-store-0.0.1-SNAPSHOT.jar
```
 

#### Copyright &copy; 2020 [Tiago dos Santos](https://github.com/TiagodosSantos)
