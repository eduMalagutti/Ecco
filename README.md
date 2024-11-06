
# ECCO

### Description

ECCO is a comprehensive marketplace platform that connects homeowners and individuals seeking household services with qualified service providers. Developed as a trainee project for Cati Jr in 2024, ECCO aims to facilitate reliable and seamless service booking and management between service providers (such as plumbers, electricians, cleaners) and customers in need of household services.

### Disclaimer

This project is only partially finished due to the trainee project deadline, it might be finished later. The backend is finished, the frontend still has work to be done.

## Project Structure

- **backend**: REST API developed with Spring Boot, providing server-side logic, API routes, and data processing.
- **frontend**: React-based application that delivers a user-friendly interface for customers and service providers.

## Backend

### Authors

- [eduMalagutti](https://github.com/eduMalagutti)
- [RenanZago](https://github.com/RenanZago)

### Core Features

- **User Registration and Authentication**: Allows new users to create accounts and returning users to log in securely.
- **Service Search and Discovery**: Users can browse and search for available services, filtered by categories.
- **Reservation Management**: Enables users to book services, view upcoming reservations, and manage past bookings.
- **User Review System**: Allows customers to rate and review services after completion to ensure quality and reliability.

### Stack
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

## Installation

### Requirements
- JDK 17
- Maven
- PostgreSQL
- SMTP Server (Gmail)

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

## Frontend

### Authors

- [heitorvf19](http://github.com/heitorvf19)
- [lucasmd30](http://github.com/lucasmd30)
- [n-qber](https://github.com/n-qber)

### Core Features

- **User Registration and Authentication**: Allows new users to create accounts and returning users to log in securely.
- **Service Search and Discovery**: Users can browse and search for available services, filtered by categories. (planned)
- **Reservation Management**: Enables users to book services, view upcoming reservations, and manage past bookings. (planned)
- **User Review System**: Allows customers to rate and review services after completion to ensure quality and reliability. (planned)

### Stack

- **React 18.3.1**
- **TypeScript**: Provides static typing for safer and more predictable code.
- **Vite**: Used as the build tool for faster development and optimized production builds.
- **React Router**: Handles client-side routing within the application.
- **Axios**: For HTTP requests to communicate with the backend API.
- **JWT Decode**: Decodes JWT tokens for authentication management on the client side.

### Setup

1. **Clone the Repository**:
   ```bash
   git clone https://gitlab.com/cati-jr/trainees-2024/squad-2/frontend.git
   cd frontend
   ```

2. **Install Dependencies**:
   ```bash
   npm install
   ```

3. **Run Development Server**:
   ```bash
   npm run dev
   ```

---

## Images

![alt text](<./screenshots/Captura de tela 2024-11-04 005038.png>) ![alt text](<./screenshots/Captura de tela 2024-11-05 000521.png>) ![alt text](<./screenshots/Captura de tela 2024-11-05 000630.png>)

---
