# URL Polling Service 


#### Project Description

A simple service poller that keeps a list of services (defined by a URL), and periodically performs a HTTP GET request to each URL and stores a record of the response ("OK" or "FAIL"). Apart from the polling logic, the services are visualised and easily managed in a basic UI presenting all the services together with their status.

#### Project Features

● Multi-user support. Basic Login and Logout functionality (without Password). Users can only see services added by them.

● Full create/update/delete functionality for services.

● Dashboard with URL, URL name, URL creation & last edit time.

● The results from the poller are automatically shown to the user on Dashboard (no need to reload the page to see results). 
Status is updated every 10 seconds. 

● URL Validation before adding or editing any URL.

● All added services, along with status are stored in database and available even after server restart.

● The service properly handles concurrent writes.

● Exception handling for URL status check.

#### Tech-stack

This is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Gradle](https://gradle.org/). 
The project is developed with Java 11. The UI is written in basic HTML, CSS and Javascript. Database MySQL version 5.7.19 was used during development and is available for download at [Mysql](https://downloads.mysql.com/archives/community/)

#### Project Setup

You can install and run mysql database either in local or in docker. 
In application.properties you can update the username, password and database name in case of any changes.

It is currently configured as : <br/>
Port: 3309 <br/>
User: dev <br/>
Password: secret <br/>
DBname : TestDB <br/>

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3309/TestDB
spring.datasource.username=dev
spring.datasource.password=secret
spring.datasource.driver-class-name =com.mysql.jdbc.Driver
Spring.jpa.show-sql: true
```

Service can be used by building a jar file and running it from command line ( it will work well with Java 8, 11 or 17).

Please update and follow the below steps on command line:

```
git clone https://github.com/srishtikasana/servicepoller.git
cd  <to_your_folder>
./gradlew build
java -jar build/libs/*SNAPSHOT.jar
```

Once the application is running, it can be accessed at:

```
http://localhost:8080/index.html

```