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

```
cd backend
./mvnw spring-boot:run

// server started at localhost:8080
```

# Assumptions

Since the requirements are not perfectly specific, I have made the following assumptions based off my understanding of the use case:

1. When moving inventory, if the weight at the from location is less than the specified, all inventory will be moved without throwing exceptions
2. Products have unique codes but not unique names, therefore the system identify products solely by code. Names are set at initial insert.
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

# Potential Improvements

1. Use custom exception object from backend so frontend can show error messages from the server
2. Implement unit test on frontend
3. Implement scheduled house cleaning for backend database in case of insufficent drive space
4. Learn Java optional syntax for cleaner code
5. Use typescript in frontend to code quality
6. Use MySQL as persistent data storage
7. Use better git working flow such as git-flow for better version management
