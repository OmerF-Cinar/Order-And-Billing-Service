# Order & Billing System – Spring Boot

A backend RESTful application built with Java and Spring Boot that manages Users, Products, Orders, and OrderItems.

The project demonstrates:
- Layered architecture (Controller – Service – Repository)
- Input validation and error handling
- Logging for API endpoints
- Unit testing with JUnit and Mockito
- Business logic such as balance updates and order handling

## Features
- Create and retrieve users
- Manage products
- Create orders with order items
- Deposit balance into user accounts
- Validation for username, password, and balance

## Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA
- JUnit 5 & Mockito
- Maven

## Example Endpoints
- `POST /user/add`
- `GET /user/all`
- `PUT /user/{id}/updatebalance/{amount}`

## Running the Project
```bash
mvn spring-boot:run
```

## Testing
```
mvn test
```

This project is a learning-focused backend to practice real-world service layer logic and REST API design.