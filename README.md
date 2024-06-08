# Report Generator Application
The Report Management System is a Spring Boot application designed to manage information about companies and their reports. It provides endpoints to add, update, delete, and retrieve information about company, reports and report details.

## Technologies Used
- Java 11
- Spring
- Spring Data JPA
- Hibernate
- Spring Data MongoDB
- Liquibase
- Spring Security
- Docker
- PostgreSQL
- MongoDB

## Installation 

1. **Install Docker**: Download and install Docker from the [official website](https://www.docker.com/products/docker-desktop).

2. **Clone the Repository**:
   ```sh
   git clone https://github.com/tetianamakar/report-generator.git
   cd report-generator

3. **Start the Application**:
      ```sh
      docker-compose up -d

## API Testing Guide

1. **Install Postman**: Ensure Postman is installed on your machine.

2. **Import Collection**: Import the Postman collection included in the repository.

3. **Sign Up**:
   - Endpoint: `http://localhost:8080/auth/signup`
   - Action: Sign up using the provided endpoint.

4. **Sign In**:
   - Endpoint: `http://localhost:8080/auth/signin`
   - Action: Sign in using the provided endpoint. Copy the token from the response body.

5. **Authenticate Requests**:
   - Action: Select any method from the imported collection and insert the copied token as a Bearer Token.
