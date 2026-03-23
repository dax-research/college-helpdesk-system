# College Help Desk System

A Spring Boot REST API for managing college support tickets and grievances.

## Tech Stack
- Java 17
- Spring Boot 3.2.x
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- Maven

## Features
- Role-based access (ADMIN, STAFF, STUDENT)
- Students can raise tickets
- Staff can respond and resolve tickets
- Admin manages departments and users

## Entities
- User (Parent)
- Student (extends User)
- Staff (extends User)
- Department
- Ticket
- TicketResponse

## API Testing
APIs are tested using Postman

## Database
MySQL — database name: `helpdesk_db`

## How to Run
1. Create MySQL database `helpdesk_db`
2. Update `application.properties` with your MySQL password
3. Run `CollegeHelpdeskSystemApplication.java`
4. Test APIs using Postman on `http://localhost:8080`
