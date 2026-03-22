# Role Management System

A Spring Boot–based backend application that implements secure authentication and authorization using Role-Based Access Control (RBAC) with JWT, along with an integrated audit logging mechanism.

---

## Features

- JWT-based authentication and authorization  
- Role-Based Access Control (RBAC)  
- Permission-based access management  
- Audit logging for key operations (CREATE, UPDATE, DELETE)  
- Layered architecture with clear separation of concerns  
- DTO-based request and response handling  

---

## Technology Stack

- Java  
- Spring Boot  
- Spring Security  
- MySQL  
- JSON Web Tokens (JWT)  

---

## API Endpoints

| Endpoint       | Description         |
|---------------|---------------------|
| `/auth`       | User authentication |
| `/users`      | User management     |
| `/roles`      | Role management     |
| `/audit-logs` | Audit log tracking  |

---

## Key Highlights

- Automatic audit logging integrated within the service layer  
- Secure API design using Spring Security and JWT  
- Scalable and maintainable project structure  
- Robust handling of roles and permissions  

---

## Running the Application Locally

### Prerequisites

- Java 17 or higher  
- Gradle  
- MySQL  

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Niraja-cscs/role-management-system.git
   cd role-management-system



   spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password



./gradlew bootRun
