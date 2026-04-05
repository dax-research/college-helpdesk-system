# Dax Patel
**Sem:** 4
**Roll No:** CE-121
**Batch:** B2
**Project Title:** College Help Desk System

---

# College Help Desk System

A Spring Boot REST API that allows students to raise support tickets, staff to resolve them, and admins to manage the system. Built as part of Java Technologies Term Work submission.

---

## Tech Stack

- Java 17
- Spring Boot 3.2.x
- Spring Security (Basic Auth + BCrypt)
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Maven

---

## Project Structure

```
src/main/java/com/ddu/college_helpdesk_system/
├── entity/
│   ├── User.java
│   ├── Student.java
│   ├── Staff.java
│   ├── Department.java
│   ├── Ticket.java
│   └── TicketResponse.java
├── enums/
│   ├── Role.java
│   ├── TicketStatus.java
│   └── TicketCategory.java
├── repository/
│   ├── UserRepository.java
│   ├── StudentRepository.java
│   ├── StaffRepository.java
│   ├── DepartmentRepository.java
│   ├── TicketRepository.java
│   └── TicketResponseRepository.java
├── service/
│   ├── UserService.java
│   ├── DepartmentService.java
│   ├── TicketService.java
│   └── TicketResponseService.java
├── controller/
│   ├── UserController.java
│   ├── DepartmentController.java
│   ├── TicketController.java
│   └── TicketResponseController.java
└── security/
    ├── SecurityConfig.java
    ├── UserDetailsServiceImpl.java
    └── PasswordEncoderConfig.java
```

---

## Database Schema

### Tables

#### users
| Column | Type | Constraint |
|--------|------|------------|
| id | BIGINT | PK, AUTO_INCREMENT |
| name | VARCHAR | - |
| email | VARCHAR | UNIQUE, NOT NULL |
| password | VARCHAR | NOT NULL (BCrypt) |
| role | VARCHAR | ENUM: ADMIN, STAFF, STUDENT |

#### students (extends users)
| Column | Type | Constraint |
|--------|------|------------|
| user_id | BIGINT | PK, FK → users.id |
| enrollment_no | VARCHAR | UNIQUE |
| branch | VARCHAR | - |
| semester | INT | - |

#### staff (extends users)
| Column | Type | Constraint |
|--------|------|------------|
| user_id | BIGINT | PK, FK → users.id |
| employee_id | VARCHAR | UNIQUE, NOT NULL |
| designation | VARCHAR | - |
| department_id | BIGINT | FK → departments.id |

#### departments
| Column | Type | Constraint |
|--------|------|------------|
| id | BIGINT | PK, AUTO_INCREMENT |
| name | VARCHAR | UNIQUE, NOT NULL |
| description | VARCHAR | - |

#### tickets
| Column | Type | Constraint |
|--------|------|------------|
| id | BIGINT | PK, AUTO_INCREMENT |
| title | VARCHAR | NOT NULL |
| description | VARCHAR | NOT NULL |
| status | VARCHAR | ENUM: OPEN, IN_PROGRESS, RESOLVED |
| category | VARCHAR | ENUM: INFRASTRUCTURE, LAB, ADMINISTRATION, OTHER |
| created_at | DATETIME | Auto set |
| raised_by | BIGINT | FK → students.user_id |
| department_id | BIGINT | FK → departments.id |

#### ticket_responses
| Column | Type | Constraint |
|--------|------|------------|
| id | BIGINT | PK, AUTO_INCREMENT |
| message | VARCHAR | NOT NULL |
| resolved_at | DATETIME | Auto set |
| resolved_by | BIGINT | FK → staff.user_id |
| ticket_id | BIGINT | FK → tickets.id |

---

## Entity Relationships

| Relationship | Type | Description |
|---|---|---|
| users → students | Inheritance (JOINED) | students.user_id = users.id |
| users → staff | Inheritance (JOINED) | staff.user_id = users.id |
| staff → departments | ManyToOne | Many staff belong to one department |
| tickets → students | ManyToOne | Many tickets raised by one student |
| tickets → departments | ManyToOne | Many tickets belong to one department |
| ticket_responses → staff | ManyToOne | Many responses resolved by one staff |
| ticket_responses → tickets | OneToOne | One ticket has exactly one response |

---

## Role-Based Access Control

| Endpoint | ADMIN | STAFF | STUDENT |
|---|---|---|---|
| Register (any role) | ✅ | ✅ | ✅ |
| Manage departments | ✅ | ❌ | ❌ |
| View all users | ✅ | ❌ | ❌ |
| View all tickets | ✅ | ✅ | ❌ |
| Raise ticket | ❌ | ❌ | ✅ |
| View own tickets | ❌ | ❌ | ✅ |
| Update own ticket | ❌ | ❌ | ✅ |
| View dept tickets | ❌ | ✅ | ❌ |
| Respond to ticket | ❌ | ✅ | ❌ |
| Update ticket status | ❌ | ✅ | ❌ |
| View unresolved tickets | ✅ | ✅ | ❌ |
| Delete ticket | ✅ | ❌ | ✅ |

---

## API Endpoints

### Auth / User
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | /api/users/register/admin | Public | Register admin |
| POST | /api/users/register/student | Public | Register student |
| POST | /api/users/register/staff | Public | Register staff |
| GET | /api/users | ADMIN | Get all users |
| GET | /api/users/{id} | ADMIN | Get user by id |
| DELETE | /api/users/{id} | ADMIN | Delete user |

### Departments
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | /api/departments | ADMIN | Create department |
| GET | /api/departments | Public | Get all departments |
| GET | /api/departments/{id} | ADMIN | Get department by id |
| PUT | /api/departments/{id} | ADMIN | Update department |
| DELETE | /api/departments/{id} | ADMIN | Delete department |

### Tickets
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | /api/tickets/raise/{studentId}/{deptId} | STUDENT | Raise a ticket |
| GET | /api/tickets | ADMIN, STAFF | Get all tickets |
| GET | /api/tickets/{id} | ADMIN, STAFF, STUDENT | Get ticket by id |
| GET | /api/tickets/student/{studentId} | STUDENT | Get student's tickets |
| GET | /api/tickets/department/{deptId} | STAFF | Get dept tickets |
| GET | /api/tickets/unresolved | ADMIN, STAFF | Get unresolved tickets |
| PUT | /api/tickets/{id} | STUDENT | Update own ticket |
| PUT | /api/tickets/{id}/status?status= | STAFF | Update ticket status |
| DELETE | /api/tickets/{id} | ADMIN, STUDENT | Delete ticket |

### Ticket Responses
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | /api/responses/{ticketId}/{staffId} | STAFF | Add response to ticket |
| GET | /api/responses | ADMIN | Get all responses |
| GET | /api/responses/ticket/{ticketId} | ADMIN, STAFF, STUDENT | Get response by ticket |
| DELETE | /api/responses/{id} | ADMIN | Delete response |

---

## Security

- **Authentication**: HTTP Basic Auth (email + password with every request)
- **Password Encoding**: BCrypt hashing via `BCryptPasswordEncoder`
- **Authorization**: Role-based access using Spring Security's `hasRole()` and `hasAnyRole()`
- **CSRF**: Disabled for REST API / Postman testing

---

## How to Run

1. Install MySQL and create the database:
```sql
CREATE DATABASE helpdesk_db;
```

2. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/helpdesk_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
server.port=8080
```

3. Run `CollegeHelpdeskSystemApplication.java` in IntelliJ

4. All tables will be auto-created by Hibernate on first run

5. Test APIs using Postman on `http://localhost:8080`

---

## Sample Postman Requests

### Register Student
```json
POST /api/users/register/student
{
    "name": "Dax Patel",
    "email": "24ceuos091@ddu.ac.in",
    "password": "dax123",
    "enrollmentNo": "24CEUOS091",
    "branch": "Computer Engineering",
    "semester": 4
}
```

### Register Staff
```json
POST /api/users/register/staff
{
    "name": "Prof. Daksh",
    "email": "shah@ddu.ac.in",
    "password": "shah123",
    "employeeId": "EMP001",
    "designation": "Lab Incharge",
    "department": { "id": 2 }
}
```

### Raise Ticket
```json
POST /api/tickets/raise/{studentId}/{departmentId}
Auth: Student credentials
{
    "title": "AC not working in lab",
    "description": "The AC in Computer Lab 3 has not been working for 2 days",
    "category": "INFRASTRUCTURE"
}
```

### Staff Responds to Ticket
```json
POST /api/responses/{ticketId}/{staffId}
Auth: Staff credentials
{
    "message": "Our maintenance team will fix the AC by tomorrow."
}
```

---
