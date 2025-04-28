# 📖 Project: Spring Boot + H2 In-Memory Database Demo

This is a simple Spring Boot 3 application that:
- Exposes a basic REST API for **messages**
- Uses **H2** in-memory database for storage
- Preloads sample data at startup
- Built with **Java 17**

---

## 🚀 How to Run

### 1. Prerequisites
- Java 17+ installed → check with `java -version`
- (Optional) Maven installed → check with `mvn -version`

> If Maven is not installed, the project uses the built-in `mvnw` wrapper.

---

### 2. Build and Run

You can run everything easily using the provided script:

```bash
./run.sh
```

Or manually:

```bash
./mvnw clean package
java -jar target/*.jar
```

---

## 🌐 API Endpoints

### Create a new message
**POST** `/api/messages`

**Request Body:** (raw text)

```
Hello World!
```

**Response:**

```json
{
  "id": 1,
  "content": "Hello World!"
}
```

---

### Get all messages
**GET** `/api/messages`

Example response:

```json
[
  {
    "id": 1,
    "content": "Hello World!"
  },
  {
    "id": 2,
    "content": "Spring Boot is awesome!"
  }
]
```

---

## 🛢️ H2 Database Console

You can access the H2 database at:

- URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

**Login credentials:**
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User:** `sa`
- **Password:** *(leave empty)*

✅ Tables and sample data will be automatically created and loaded at startup.

---

## ⚙️ Project Structure

```
src/main/java/com/example/demo/
├── controller/MessageController.java
├── model/Message.java
├── repository/MessageRepository.java
├── DemoApplication.java
src/main/resources/
├── application.properties
├── data.sql
run.sh
pom.xml
```

---

## 🧠 Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

---

## ✨ Features

- Lightweight in-memory database
- Automatic table creation
- Data preloading via `data.sql`
- Simple and clean REST APIs
- H2 web console for easy database access

---

## 📜 License

This project is open-source and free to use.

---