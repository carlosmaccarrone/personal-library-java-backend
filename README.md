📚 Personal Library Java Backend

A Spring Boot backend for managing a personal library.
It exposes REST endpoints to handle books, authors, and genres, featuring many-to-many relationships and SQL views for advanced queries.

The application runs in Docker containers alongside the database and communicates through a shared Docker network.

---

## ⚙️ Technologies

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Docker & Docker Compose
- Maven

---

## 📂 Project Structure

## 🗄️ Database Model

- **Authors** – authors
- **Genres** – genres
- **Books** – books
- **Book_Author** – book ↔ author relationship (many-to-many)
- **Book_Genre** – book ↔ genre relationship (many-to-many)

---

## 🔗 Prerequisites

### 1. Global Docker Network

Create a global network and set up the database model:
```bash
git clone https://github.com/carlosmaccarrone/personal-library-db.git
docker network create --driver bridge --attachable library_net
docker-compose up -d
```
This will create a network shared by both projects (backend & database) and will start the database in the background.

---

## 🚀 Running the Project

### 1. Clone the Backend and run it.
```bash
git clone https://github.com/tuusuario/personal-library-backend.git
cd personal-library-backend
docker-compose up --build
```
This will start:
- PostgreSQL with the personal library database  
- Spring Boot backend connected to the database  

### 2. Access the API

By default at:
http://localhost:8081/api

---

## 📖 Main Endpoints

🔹 Books  
	`GET /api/books` → List all books  
	`GET /api/books/{isbn}` → Get a book by ISBN  
	`POST /api/books` → Create a new book  
	`PUT /api/books/{isbn}` → Update a book  
	`DELETE /api/books/{isbn}` → Delete a book  
	`GET /api/books/full` → List books with authors and genres  
	`GET /api/books/{isbn}/full` → Get full details of a book  

🔹 Authors  
	`GET /api/authors/all` → List all authors  
	`GET /api/authors/{id}` → Get an author by ID  
	`POST /api/authors` → Create a new author  
	`GET /api/authors/{id}/books` → List books by a specific author  

🔹 Genres  
	`GET /api/genres/all` → List all genres  
	`POST /api/genres` → Create a new genre  
	`GET /api/genres/{id}/books` → List books by a specific genre  

---

## 🧪 Tests

Run unit tests:

```bash
docker run --rm -v %cd%:/app -w /app maven:3.9.2-eclipse-temurin-17 mvn -Dtest=*Test test
```

---

## 📌 Future Improvements

JWT authentication (users and roles)

API documentation with Swagger

---

## 👨‍💻 Developed by Carlos Maccarrone