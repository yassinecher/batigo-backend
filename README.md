# Batigo Backend

## Overview
This Spring Boot application serves as the backend for the Batigo Construction Project Management System. It provides RESTful APIs to manage workflows, products, suppliers, and incidents, supporting the Angular frontend. Built with Spring Boot and MySQL, it ensures scalability, security, and efficient data handling for construction project operations.

## Features
- **Workflow Management**: APIs to create, update, and track project workflows.
- **Product Management**: Manage construction material inventory.
- **Supplier Management**: Handle supplier data and contracts.
- **Incident Management**: Log and resolve project incidents.
- **Authentication**: JWT-based authentication for secure access.
- **Database Integration**: Persistent storage using MySQL.

## Technologies
- **Spring Boot**: v3.x
- **Java**: v17 or later
- **Maven**: Build tool
- **Database**: MySQL v8.x
- **Spring Security**: For authentication and authorization
- **Spring Data JPA**: For database operations

## Prerequisites
Ensure the following are installed:
- **Java**: JDK 17 or later
- **Maven**: v3.8.x or later
- **MySQL**: v8.x or later
- **Git**: For version control

## Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yassinecher/batigo-backend.git
   cd batigo-backend
   ```

2. **Configure MySQL Database**:
   - Create a MySQL database (e.g., `batigo_db`).
   - Update `src/main/resources/application.properties` with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/batigo_db?useSSL=false&serverTimezone=UTC
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Configure JWT** (if applicable):
   - Update JWT secret and expiration in `application.properties`:
     ```properties
     jwt.secret=your-secure-secret-key
     jwt.expiration=86400000
     ```

4. **Build the Application**:
   ```bash
   mvn clean install
   ```

5. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   - The server will start at `http://localhost:8080`.

## API Endpoints
Below are key API endpoints (base URL: `http://localhost:8080/api`):
- **Workflows**:
  - `GET /workflows`: List all workflows.
  - `POST /workflows`: Create a new workflow.
  - `PUT /workflows/{id}`: Update a workflow.
  - `DELETE /workflows/{id}`: Delete a workflow.
- **Products**:
  - `GET /products`: List all products.
  - `POST /products`: Add a new product.
  - `PUT /products/{id}`: Update product details.
- **Suppliers**:
  - `GET /suppliers`: List all suppliers.
  - `POST /suppliers`: Add a new supplier.
  - `PUT /suppliers/{id}`: Update supplier information.
- **Incidents**:
  - `GET /incidents`: List all incidents.
  - `POST /incidents`: Log a new incident.
  - `PUT /incidents/{id}`: Update incident status.
- **Authentication**:
  - `POST /auth/login`: Authenticate user and return JWT.
  - `POST /auth/register`: Register a new user (admin only).

## Project Structure
```
batigo-backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/batigo/
│   │   │   ├── controller/    # REST controllers
│   │   │   ├── service/       # Business logic
│   │   │   ├── repository/    # JPA repositories
│   │   │   ├── model/        # Entity classes
│   │   │   └── config/       # Security and app configurations
│   │   └── resources/
│   │       └── application.properties  # Configuration file
├── pom.xml                   # Maven dependencies
└── README.md                 # This file
```

## Database Setup
1. Ensure MySQL is running.
2. Create the database:
   ```sql
   CREATE DATABASE batigo_db;
   ```
3. The application uses JPA to auto-generate tables based on entity classes.

## Integration with Frontend
- The backend is designed to work with the Angular frontend for the Batigo system.
- Ensure CORS is configured in `application.properties` to allow frontend access:
  ```properties
  spring.cors.allowed-origins=http://localhost:4200
  ```
- Update the Angular `environment.ts` file with the backend base URL (`http://localhost:8080/api`).

## Testing
Run unit tests using:
```bash
mvn test
```
For integration tests, ensure the MySQL database is running.

## Deployment
To create a deployable JAR:
```bash
mvn package
```
Run the JAR:
```bash
java -jar target/batigo-backend-0.0.1-SNAPSHOT.jar
```

## Contributing
1. Fork the repository at https://github.com/yassinecher/batigo-backend.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m "Add your feature"`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a Pull Request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For questions or feedback, contact the project maintainer via GitHub issues at https://github.com/yassinecher/batigo-backend.
