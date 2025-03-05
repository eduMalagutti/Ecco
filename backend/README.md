# Ecco Backend
## Description
The Ecco Backend is a REST API developed as a trainee project for Cati Jr in 2024. It serves as the backend infrastructure for the Ecco platform, 
a comprehensive marketplace that connects homeowners and individuals seeking household services with qualified service providers and professionals.

## Authors

- [eduMalagutti](https://github.com/eduMalagutti)
- [RenanZago](https://github.com/RenanZago)

### Core Features
- User authentication and role-based access control
- Service provider profiles with verification system
- Comprehensive service listing and search functionality
- Reservation management with status tracking
- Review and rating system for service quality assurance

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
- Docker
- SMTP Server (Gmail)

## Installation
### Cloning the Repository
```bash
git clone https://github.com/eduMalagutti/Ecco.git
cd Ecco/backend
```

### Environment Setup
- Create an `env-dev.properties` file in the project root with the following variables:
```properties
API_PORT=8080
JWT_SECRET=your_jwt_secret
DB_URL=localhost:5432/projeto-trainee
DB_USER=admin
DB_PASSWORD=admin
EMAIL_SENDER=your_email@gmail.com
EMAIL_USER=your_email_user
EMAIL_PASSWORD=your_email_password
```

### Building the Database Docker Image
```bash
docker compose up -d
```

### Running the Application
```bash
mvn spring-boot:run
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
