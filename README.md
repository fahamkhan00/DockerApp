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

- 📘 **Postman Collection**: [Download Collection](postman/dockerApp.postman_collection.json)  
- 📚 **Swagger UI**: [Documentation](http://3.111.102.145:8080/swagger-ui/index.html#/)

> Make sure your EC2 instance allows port `8080` in the security group, and Swagger is enabled in your Spring Boot app.

---


## 🐳 Docker Container API

| Method | Endpoint                  | Description                      |
|--------|---------------------------|----------------------------------|
| POST   | `/api/docker/openjdk`     | Start a new jdk Docker container |
| POST   | `/api/docker/mysql`       | Start the mysql container        |
| POST   | `/api/docker/stop`        | Stop and remove a container      |
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

1. Open the Swagger UI: `http://http://3.111.102.145/:8080/swagger-ui/index.html`
2. Register a user via `/api/auth/register`
3. Login to get the JWT token via `/api/auth/login`
4. Authorize Swagger with the token
5. Test `/api/docker/openjdk`, `/mysql`,`/stop`, and `/list` endpoints

Or use the included Postman Collection:
- Import `postman_collection.json` into Postman
- Set the environment variable `{{baseUrl}}` to `http://3.111.102.145:8080`
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

<img width="1917" height="1004" alt="cicd" src="https://github.com/user-attachments/assets/a326a82e-265e-4590-84ec-7b83f1b2402c" />
<img width="1920" height="1048" alt="login" src="https://github.com/user-attachments/assets/d5da28f0-c40e-42f0-a645-9cdb2e841fe5" />
<img width="1920" height="1048" alt="stopcontainer" src="https://github.com/user-attachments/assets/ea1ec040-a0ee-4fdb-a7d0-fd58844ba943" />
<img width="1920" height="1048" alt="container" src="https://github.com/user-attachments/assets/9c3e0cec-f1cf-4cf7-964d-ada75a574143" />
<img width="1920" height="1048" alt="serviceCode" src="https://github.com/user-attachments/assets/6eb56f69-17d1-4a7a-9b43-3183d0758388" />
<img width="1920" height="1048" alt="renameCode" src="https://github.com/user-attachments/assets/92541f8e-134b-4297-ad4f-3b3a7b1cd432" />
<img width="1920" height="1048" alt="swagger" src="https://github.com/user-attachments/assets/8ee4b6ff-d45c-49c2-9a76-9dfeb1f324a4" />






