# ECCO

### Description

ECCO is a comprehensive marketplace platform that connects homeowners and individuals seeking household services with qualified service providers. Developed as a trainee project for Cati Jr in 2024, ECCO aims to facilitate reliable and seamless service booking and management between service providers (such as plumbers, electricians, cleaners) and customers in need of household services.

### Disclaimer!!

This project is only partially finished due to the trainee project deadline, it might be finished later. The backend is complete, the frontend still has work to be done.

## Project Structure

- **backend**: REST API developed with Spring Boot, providing server-side logic, API routes, and data processing.
- **frontend**: React-based application that delivers a user-friendly interface for customers and service providers.

## Figma

- https://www.figma.com/design/F5bZSFNEkdl4ck008wi50A/UI-Design?node-id=48-14&t=jLvDeygRpFiubwIR-1

## Backend

### Authors

- [eduMalagutti](https://github.com/eduMalagutti)
- [RenanZago](https://github.com/RenanZago)

### Core Features

- User authentication and role-based access control
- Service provider profiles with verification system
- Comprehensive service listing and search functionality
- Reservation management with status tracking
- Review and rating system for service quality assurance

### Stack

- **Java 17**
- **Spring Boot 3.3.0**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway**
- **SpringDoc OpenAPI (Swagger)**
- **Spring Mail**
- **Lombok**
- **Spring HATEOAS**

### Requirements

- JDK 17
- Maven
- Docker
- SMTP Server (Gmail)

### Installation

#### Cloning the Repository

```bash
git clone https://github.com/eduMalagutti/Ecco.git
```
```bash
cd Ecco/backend
```

#### Environment Setup

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

#### Building the Database Docker Image

```bash
docker compose up -d
```

#### Running the Application

```bash
mvn spring-boot:run
```

### Main Endpoints

#### Users (`/v1/users`)

- `POST /signup` - User registration
- `POST /login` - User login
- `GET /attributes` - Get user attributes
- `PUT /update/attributes` - Update attributes
- `PATCH /update/profile-pic` - Update profile picture
- `PATCH /verify-profile` - Verify user profile

#### Services (`/v1/services`)

- `GET /paged-search` - Paginated service search
- `POST /create` - Create new service
- `GET /{id}` - Get specific service
- `DELETE /delete/{id}` - Delete service

#### Reservations (`/v1/reservations`)

- `POST /create` - Create reservation
- `GET /{id}` - Get reservation
- `PATCH /accept-reservation` - Accept reservation
- `PATCH /reject-reservation` - Reject reservation
- `PATCH /cancel-reservation` - Cancel reservation
- `PATCH /review-reservation-customer` - Review customer
- `PATCH /review-reservation-provider` - Review service provider

### Documentation

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

- **User Registration and Authentication**
- **Service Search and Discovery**: (planned)
- **Reservation Management**: (planned)
- **User Review System**: (planned)

### Stack

- **React 18.3.1**
- **TypeScript**
- **Vite**
- **React Router**
- **Axios**

### Setup

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/eduMalagutti/Ecco.git
   ```
   ```bash
   cd Ecco/frontend
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
