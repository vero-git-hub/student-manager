# Microservice - Student Manager

The Student Management microservice includes a frontend built with **Angular** and a backend powered by **Java Spring Boot**. This CRUD application uses **MongoDB** as the database and is deployed using **Docker** and **Kubernetes**.

It is developed according to Java coding standards with secure authentication using **JWT**. The architecture follows a separation between controllers, services, and repositories.

## Table of Contents

- [Technologies](#technologies)
- [Features](#features)
- [Architecture](#architecture)
- [Project Setup](#project-setup)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Test Coverage](#test-coverage)
- [Deployment](#deployment)
- [License](#license)

## Technologies

- **Frontend**: Angular v18.2.4, Angular Material
- **Backend**: Java v17.0.7, Spring Boot v3.3.3, Apache Maven
- **Database**: MongoDB v7.0.14
- **Deployment**: Docker v4.33.1, Nginx, Kubernetes v1.30.2
- **Documentation**: Swagger (Springdoc OpenAPI) v2.5.0
- **Security**: Spring Security JJWT v0.11.5, frontend validation
- **Tests**: JUnit, Mockito, Spring Security Test, MockMvc
- **Tools**: Node.js v20.14.0, Intellij IDEA, Postman

## Features

The application performs the core functions of a RESTful API:
- Registration and authentication
- Retrieving information about students/student
- Updating and deleting student data

## Architecture

- **student-manager-frontend**: Directory containing the Angular frontend.
  - **nginx.conf:** Nginx configuration for serving the frontend.
- **backend (project root)**: Spring Boot backend using MongoDB and JWT for authentication.
- **docker-compose.yml**: For managing MongoDB, backend, and frontend containers.

## Project Setup

The project uses docker-compose to launch all services. Steps to launch using Docker:

1. Rebuild and start the containers:

   ```bash
   docker-compose up --build

This will launch three containers:

- **mongodb:** MongoDB database.
- **student-manager-backend:** Spring Boot backend running on port 8080.
- **student-manager-frontend:** Angular frontend served via Nginx, running on port 80.

2. Open your browser and go to http://localhost to view the application.

## API Documentation

The backend API is available at http://localhost:8080. Swagger is used for API testing and documentation.

### Swagger Documentation

You can view the full documentation and perform test requests via Swagger, available at the following address: http://localhost:8080/swagger-ui.html.

## Testing

You can test requests in several ways:
1. **Postman:** An external tool for creating and sending HTTP requests.
2. **Swagger UI:** Testing can be done directly through the Swagger interface (described above).
3. **Angular Interface:** The frontend application's user interface, which sends requests to the backend.

## Test Coverage

The application is covered by tests for the developed APIs. This includes:
- **Unit Testing:** Verifying individual parts (modules) of the code.
- **Security Testing:** Checking authorization and authentication, and protecting against unauthorized access.

## Deployment

For easy deployment, the application supports containerization using Docker. The project includes a mechanism for creating Docker images via Dockerfile for both the frontend and backend.

The project also provides configuration files for deployment through Kubernetes, allowing for easy scaling and management of the application in a clustered environment.

## License
This project is licensed under the terms of the MIT License.