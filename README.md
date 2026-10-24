# User Authentication & Role Management System

A production-ready, beginner-friendly RESTful backend API for user identity onboarding, authentication, and Role-Based Access Control (RBAC) built using **Spring Boot 3, Spring Security 6, JJWT, Spring Data JPA, and MySQL/H2**.

---

## 🌟 Key Features
- **User Registration & Validation**: Input validation using Hibernate Validator (`@Valid`, `@NotBlank`, `@Email`, `@Size`).
- **Password Security**: Automatic hashing with BCrypt before persistence.
- **Stateless Authentication with JWT**: Issues HMAC-SHA256 signed JSON Web Tokens with configurable expiration.
- **Role-Based Access Control (RBAC)**: Distinguishes between `ROLE_USER` and `ROLE_ADMIN` using Spring Security method & URL authorization.
- **Centralized Exception Handling**: Custom `@RestControllerAdvice` delivering standardized JSON error responses.
- **Zero-Config Testing**: Runs immediately using an in-memory H2 database, with ready-to-use configuration for MySQL 8.0.

---

## 🛠️ Tech Stack
- **Language**: Java 17
- **Framework**: Spring Boot 3.2.5
- **Security**: Spring Security 6, JJWT (Java JWT 0.12.5), BCrypt
- **Persistence**: Spring Data JPA, Hibernate ORM
- **Database**: H2 (In-memory default) / MySQL 8.0
- **Build Tool**: Maven

---

## 🚀 How to Run

### Option 1: Run with Maven
```bash
cd user-auth-jwt-system
mvn clean spring-boot:run
```
The application will start on `http://localhost:8080`.
H2 Console is available at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:authdb`, User: `sa`, Password: `password`).

---

## 📡 API Endpoints Documentation

### 1. Register a Normal User
- **URL**: `POST /api/auth/register`
- **Body**:
```json
{
  "username": "gangadhar",
  "email": "gangadhar@example.com",
  "password": "Password123",
  "admin": false
}
```
- **Response**: `201 Created`

### 2. Register an Admin User
- **URL**: `POST /api/auth/register`
- **Body**:
```json
{
  "username": "admin_user",
  "email": "admin@example.com",
  "password": "AdminPassword123",
  "admin": true
}
```

### 3. Login & Obtain JWT Token
- **URL**: `POST /api/auth/login`
- **Body**:
```json
{
  "username": "gangadhar",
  "password": "Password123"
}
```
- **Response**: `200 OK`
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "gangadhar",
  "roles": ["ROLE_USER"],
  "message": "Authentication successful!"
}
```

### 4. Get Current User Profile (Protected)
- **URL**: `GET /api/users/me`
- **Headers**:
  - `Authorization: Bearer <your_jwt_token>`
- **Response**: `200 OK`

### 5. Get All Users (Admin Only)
- **URL**: `GET /api/admin/users`
- **Headers**:
  - `Authorization: Bearer <admin_jwt_token>`
- **Response**: `200 OK` (Returns list of all users). Non-admin tokens receive `403 Forbidden`.

---

## 💡 Top Interview Questions & Answers for this Project

1. **How does JWT authentication work in your application?**
   > *Answer: The client posts credentials to `/api/auth/login`. If Spring Security's `AuthenticationManager` verifies them, `JwtUtils` generates a signed token containing the username and claims. For subsequent requests, `JwtAuthenticationFilter` intercepts the request, extracts the Bearer token, validates the signature, and sets the `Authentication` in the `SecurityContextHolder`.*

2. **Why did you use BCrypt instead of storing passwords as plain text or MD5?**
   > *Answer: BCrypt is an adaptive cryptographic hash function that incorporates salt to protect against rainbow table attacks. It is deliberately slow and computationally intensive, protecting against brute-force attacks.*

3. **What is the difference between `@RestControllerAdvice` and standard try-catch?**
   > *Answer: `@RestControllerAdvice` provides global, centralized exception handling across all controllers. Instead of duplicating try-catch blocks in every controller method, unhandled exceptions are routed here and formatted into clean HTTP responses.*
