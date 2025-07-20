# 🚀 Spring Boot Docker Container Manager

A Spring Boot application that allows users to spin up Docker containers (e.g., OpenJDK, MySQL, etc.) through REST API calls. 
The application is hosted on an AWS EC2 instance and uses Amazon RDS for MySQL to store container metadata such as container ID, type, and host port mappings.

---

## 🌐 Features

- 🔒 JWT-based user authentication (login, register)
- 🐳 Start, stop, and list Docker containers via REST API
- 🧠 Store container info in Amazon RDS (MySQL)
- ☁️ Deployed on AWS EC2 with Docker
- 🧾 Logs and metrics for container lifecycle

---

## ⚙️ Tech Stack

- **Backend:** Spring Boot, Spring Security, Spring Data JPA
- **Database:** MySQL (Amazon RDS)
- **Cloud Hosting:** AWS EC2
- **Container Runtime:** Docker
- **Authentication:** JWT
- **Deployment:** Docker Compose

---

## 🔐 Authentication API

| Method | Endpoint           | Description         |
|--------|--------------------|---------------------|
| POST   | `/api/auth/register` | Register a new user |
| POST   | `/api/auth/login`    | Login and get token |

---

---

## 📖 API Documentation

- 📘 **Postman Collection**: [Download Collection](postman/postman_collection.json)  
- 📚 **Swagger UI**: [http://3.6.139.252:8080/swagger-ui/index.html](http://http:3.6.139.252:8080/swagger-ui/index.html)

> Make sure your EC2 instance allows port `8080` in the security group, and Swagger is enabled in your Spring Boot app.

---


## 🐳 Docker Container API

| Method | Endpoint                  | Description                      |
|--------|---------------------------|----------------------------------|
| POST   | `/api/containers/start`   | Start a new Docker container     |
| POST   | `/api/containers/stop`    | Stop and remove a container      |
| GET    | `/api/containers`         | List all containers for a user   |

> 🔐 All endpoints are secured and require a valid JWT token in the `Authorization: Bearer <token>` header.

## 🧠 Architecture Overview

This project follows a modular architecture where each layer has a clear responsibility:

- **Spring Boot Backend**: Exposes REST APIs to manage Docker containers and handle user authentication.
- **Docker Engine**: Interacts with the backend to start/stop containers dynamically based on user requests.
- **MySQL (Amazon RDS)**: Persists user data and container metadata.
- **Swagger UI**: Provides a live API documentation interface.
- **Postman**: Offers a ready-to-import collection for testing endpoints.

---

## 🧪 Testing the API

1. Open the Swagger UI: `http://<your-ec2-ip>:8080/swagger-ui/index.html`
2. Register a user via `/api/auth/register`
3. Login to get the JWT token via `/api/auth/login`
4. Authorize Swagger with the token
5. Test `/api/containers/start`, `/stop`, and `/list` endpoints

Or use the included Postman Collection:
- Import `postman_collection.json` into Postman
- Set the environment variable `{{baseUrl}}` to `http://<your-ec2-ip>:8080`
- Run the authentication and container request flows


## 🗃️ Database Schema (MySQL)

### `users` Table
- `id`: INT, Primary Key
- `username`: VARCHAR
- `email`: VARCHAR
- `password`: VARCHAR (encrypted)

### `containers` Table
- `id`: INT, Primary Key
- `user_id`: INT (Foreign Key)
- `container_id`: VARCHAR
- `image_name`: VARCHAR
- `host_port`: INT
- `status`: ENUM('RUNNING', 'STOPPED')
- `created_at`: TIMESTAMP

---





