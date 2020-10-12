# warehouse-inventory-system

# How to run it

## Requires the following tools

- openJDK
- maven
- yarn

## Frontend

```
cd frontend
yarn install

// Go to localhost:3000 on browser
```

## Backend

To start server at localhost:8080
```
cd backend
./mvnw spring-boot:run
```

To run unit tests
```
cd backend
./mvnw test
```

To compile a jar file
```
cd backend
./mvnw clean install
cd target
// jar file is here
```

Note: Use `./mvnw.cmd` instead of `./mvnw` on command prompt on Windows

# Assumptions

Since the requirements are not perfectly specific, I have made the following assumptions based off my understanding of the use case:

1. When moving inventory, if the weight at the from location is less than the specified, all inventory will be moved without throwing exceptions
2. Products have unique codes but not unique names, therefore the system identify products solely by code. Names are updated when inventories are added.
3. Web app will be used on mobile browsers, therefore autolayout is implemented.

# Progress
## Day 1

Design and draft system specifications
- Database schema
- API endpoints
- Front end UI

## Day 2

- Learn how to setup Spring Boot project
- Learn how to configure application to persist data on disk
- Learn how to use Java
- Use node to test backend implentations
- Setup React frontend
- Struggle with CORS issues
- Make assumptions about requirements by thinking like the user

## Day 3
- Implement final API
- Finalise frontend
- Handle exceptions on both frontend and backend
- Write down potential improvements
- Write documentation
- Baffled by how spring boot does not support validation of list of JSONs
- Struggle with dependency injections for unit tests
- Add backend unit tests
- Gladly discovered GSON library
- Add actuator for easier production management

# Potential Improvements

## Major
1. Use custom exception object from backend so frontend can show error messages from the server
2. Ensure all endpoints conforms to HTTP status protocols
3. Find a way to validate list of JSONs
4. Support pagination for GET product count API
5. Write more unit test to cover all cases
6. Use Kotlin
7. Use typescript in frontend to improve code quality
8.  Logging
    1.   all requests and responses on both frontend and backend
    2.   all function calls
    3.   all database operations

## Minor

1. Implement unit test on frontend
2. Implement scheduled house cleaning for backend database in case of insufficent drive space
3. Learn Java optional syntax for cleaner code
4. Use MySQL as persistent data storage
5. Use better git working flow such as git-flow for better version management
6. Ensure all public and private access are appropriately assigned