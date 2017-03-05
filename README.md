# Behavior Driven Development
[![Build Status](https://travis-ci.org/ruslantkachuk/JMP3_BDD.svg?branch=master)](https://travis-ci.org/ruslantkachuk/JMP3_BDD)

### TECHNOLOGY STACK
Spring Boot (web, test, jpa), HSQLDB, Cucumber, Gradle

**Build project**: gradle build

**Run tests**: gradle test

**Run project**: gradle bootRun

### TASK GENERAL INFORMATION
Should be created simple CRUD application with one entity and covered by the cucumber auto tests for the REST API.

### REST API:

**1.Person**
- 1.1 Get person
 - **GET /persons/{id}** 
 
- 1.2 Create person
 - **POST /persons** 

   | Name | Type | Description |
   | ---- | ---- | ----------- |
   | firstName | String | First name of person |
   | lastName | String | Last name of person |
   | email | String | Primary email of person |
     
     ```sh
     {
         "firstName": "PersonFirstName",
         "lastName": " PersonLastName ",
         "email": "person@epam.com"
     }
     ```

- 1.3 Update person
 - **PUT /persons** 

   | Name | Type | Description |
   | ---- | ---- | ----------- |
   | id | int | Identificator of person |
   | firstName | String | First name of person |
   | lastName | String | Last name of person |
   | email | String | Primary email of person |
     
     ```sh
     {
         "id": 100,
         "firstName": "PersonFirstName",
         "lastName": " PersonLastName ",
         "email": "person@epam.com"
     }
     ```
 
- 1.4 Delete person
 - **DELETE /persons/{id}** 
