# 🐶 PetRegistry - Dog Registration API

**PetRegistry** is a backend system built using **Java 17** and **Spring Boot 3.5.3** that allows registering dogs and enriching their data by consuming real-time breed information from [The Dog API](https://api.thedogapi.com/v1/breeds). It follows a **hexagonal (ports and adapters) architecture**, with full support for testing, logging, caching, and containerized deployment.

---

## 📖 Problem Statement

There is a growing interest among pet owners to better understand their dogs’ breed characteristics—such as average weight, temperament, breed group, life expectancy, and more. This system aims to provide accurate and enriched breed information at the time of pet registration by consuming data from an external public API.

---

## ✅ Requirements Implemented

### 🐕 Register a pet with the following data:
- Name
- Age
- Address
- City
- Breed ID (from The Dog API)

🔍 The system automatically fetches and stores:
- Life span (`life_span`)
- Average weight (`weight.metric`)
- Average height (`height.metric`)
- Breed group (`breed_group`)
- Temperament (`temperament`)
- Bred for purpose (`bred_for`)

### 🔎 Query pets using optional filters:
- By breed group
- By breed ID
- By age range
- By weight range
- By height range

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot** (Web, Validation, Data JPA, WebClient, Redis)
- **H2 In-Memory Database**
- **Redis** (for caching breed data)
- **JUnit 5 + Mockito** (unit tests)
- **Docker & Docker Compose**

---

## 🧱 Architecture Overview

The solution follows **Hexagonal Architecture**:

```
com.example.petregistry
├── breed/         # Breed-related logic (domain, API client)
├── pet/           # Pet domain, registration and querying services
├── common/        # Shared config (cache, logging, error handling)
└── ...
```

---

## 🧪 Test Coverage

The project includes **unit tests** for pet registration, filtering, and breed API integration.

To generate a test coverage report:

  ```bash
  ./gradlew test jacocoTestReport
  ```

The report will be available at:  
`build/reports/jacoco/test/html/index.html`

---

## 🗃️ Database Schema (H2)

**Table**: `pets`

| Column         | Type     | Description                    |
|----------------|----------|--------------------------------|
| id             | Long     | Primary key                    |
| name           | String   | Pet's name                     |
| age            | Short    | Pet's age                      |
| address        | String   | Street address                 |
| city           | String   | City                           |
| breed_id       | String   | External breed ID              |
| breed_name     | String   | Breed name                     |
| average_weight | String   | Raw value from API             |
| average_height | String   | Raw value from API             |
| min_weight     | Double   | Parsed numeric value           |
| max_weight     | Double   | Parsed numeric value           |
| min_height     | Double   | Parsed numeric value           |
| max_height     | Double   | Parsed numeric value           |
| life_span      | String   | Breed life span                |
| temperament    | String   | Breed temperament              |
| breed_group    | String   | Breed group                    |
| bred_for       | String   | Purpose of the breed           |

---

## 🌐 External API: The Dog API

- **Base URL**: `https://api.thedogapi.com/v1/breeds`
- **Auth Header**: `x-api-key: <YOUR_API_KEY>`
- **API Key** is required and injected via the `DOG_API_KEY` environment variable.
- The entire breed list is cached using **Redis** and filtered in-memory.

---

## 📦 How to Run (Docker)

### Prerequisites
- Docker & Docker Compose installed
- Set your API key:
  ```bash
  export DOG_API_KEY=your_api_key_here
  ```

### Launch the application

```bash
docker compose up --build
```

The API will be available at:  
**http://localhost:8080**

---

## 📫 Endpoints Overview

| Method | Endpoint        | Description                          |
|--------|------------------|--------------------------------------|
| `POST` | `/pets`          | Register a new pet                   |
| `GET`  | `/pets`          | List pets with optional filters      |
| `GET`  | `/breeds/{id}`   | Get breed details by external ID     |

Filters on `/pets` include:
- `breedId`
- `breedGroup`
- `minAge`, `maxAge`
- `minWeight`, `maxWeight`
- `minHeight`, `maxHeight`

---

## 🔁 Caching Strategy

- Breed details fetched from The Dog API are cached for 10 minutes.
- Redis is configured using `RedisCacheManager` with `GenericJackson2JsonRedisSerializer`.
- Spring Boot automatically handles cache invalidation via the `@Cacheable("breeds")` annotation.

### Docker Redis service

```yaml
redis:
  image: redis:7-alpine
  ports:
    - "6379:6379"
```

---

## 📭 Postman Collection

You can use the provided Postman collection to interact with the API.

> 📁 File: `postman/petregistry_api.json`

---

## 🧩 Environment Variables

These are configured in `docker-compose.yml`:

```yaml
environment:
  - DOG_API_KEY=${DOG_API_KEY}
  - SPRING_REDIS_HOST=redis
  - SPRING_DATA_REDIS_HOST=redis
  - SPRING_DATA_REDIS_PORT=6379
```

---

## Author
Developed by Jonathan Navarro as part of a technical challenge.