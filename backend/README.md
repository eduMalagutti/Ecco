# Ecco Backend
## Description
The Ecco Backend is a REST API developed as a trainee project for Cati Jr in 2024. It serves as the backend infrastructure for the Ecco platform, a comprehensive marketplace that connects homeowners and individuals seeking household services with qualified service providers and professionals.

### Purpose
- **Service Marketplace**: Facilitates the connection between service providers (such as plumbers, electricians, cleaners, etc.) and customers requiring household services
- **Quality Assurance**: Implements verification systems for service providers to ensure reliable and trustworthy service delivery
- **Seamless Booking**: Manages the complete service booking lifecycle, from initial request to completion and review
- **Trust Building**: Features a robust review and rating system for both service providers and customers

### Core Features
- User authentication and role-based access control
- Service provider profiles with verification system
- Comprehensive service listing and search functionality
- Reservation management with status tracking
- Review and rating system for service quality assurance
- Secure payment integration (planned)

The project is built using Spring Boot and incorporates modern security practices, and authentication.

## Stack
- Java 17
- Spring Boot 3.3.0
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Flyway for Migrations
- SpringDoc OpenAPI (Swagger)
- Spring Mail
- Lombok
- Spring HATEOAS

## Requirements
- JDK 17
- Maven
- PostgreSQL
- SMTP Server (Gmail)

## Installation
### Cloning the Repository
```bash
git clone https://gitlab.com/cati-jr/trainees-2024/squad-2/backend.git
cd backend
```

### Environment Setup
1. Create an `env-dev.properties` file in the project root with the following variables:
```properties
API_PORT=8080
JWT_SECRET=your_jwt_secret
DB_URL=localhost:5432/database_name
DB_USER=your_db_user
DB_PASSWORD=your_db_password
EMAIL_SENDER=your_email@gmail.com
EMAIL_USER=your_email_user
EMAIL_PASSWORD=your_email_password
```

2. Configure the `application.properties` file:
```properties
spring.config.import=./env-dev.properties
spring.profiles.active=dev
spring.application.name=trainee_project
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true
```

## Main Endpoints
### Users (`/v1/users`)
- `POST /signup` - User registration
- `POST /login` - User login
- `GET /attributes` - Get user attributes
- `PUT /update/attributes` - Update attributes
- `PATCH /update/profile-pic` - Update profile picture
- `PATCH /verify-profile` - Verify user profile

### Services (`/v1/services`)
- `GET /paged-search` - Paginated service search
- `POST /create` - Create new service
- `GET /{id}` - Get specific service
- `DELETE /delete/{id}` - Delete service

### Reservations (`/v1/reservations`)
- `POST /create` - Create reservation
- `GET /{id}` - Get reservation
- `PATCH /accept-reservation` - Accept reservation
- `PATCH /reject-reservation` - Reject reservation
- `PATCH /cancel-reservation` - Cancel reservation
- `PATCH /review-reservation-customer` - Review customer
- `PATCH /review-reservation-provider` - Review service provider

## Documentation
Complete API documentation is available through Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

## Authors
- Eduardo Souza Malagutti
- Renan Zago
