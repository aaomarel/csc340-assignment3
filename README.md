# Ocean Life API

A RESTful API for managing marine animal data built with Spring Boot, JPA, and PostgreSQL. This API provides full CRUD operations for marine animals with search and filtering capabilities.

## Installation

### Prerequisites
- Java 17 or higher
- PostgreSQL database (using Neon Tech)
- Gradle (or use IDE with Gradle support)
- Thunder Client, Postman, or similar API testing tool

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <your-repository-url>
   cd csc340-assignment3
   ```

2. **Configure the Database**
   
   Create a `src/main/resources/application.properties` file with your Neon Tech database connection:
   ```properties
   spring.datasource.url=YOUR_NEON_TECH_CONNECTION_STRING
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update

   #Log out sql queries
   logging.level.org.hibernate.SQL=DEBUG
   logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
   logging.level.org.hibernate.orm.jdbc.bind=TRACE
   ```

3. **Exclude application.properties from Git tracking**
   ```bash
   git update-index --skip-worktree src/main/resources/application.properties
   ```

4. **Build and Run the Application**
   
   Using IDE (IntelliJ IDEA, VS Code with Java extensions):
   - Open the project
   - Run the `OceanLifeApiApplication.java` main class
   
   Or using Gradle:
   ```bash
   ./gradlew bootRun
   ```

5. **Access the API**
   
   The API will be available at: `http://localhost:8080`

## API Endpoints

### Base URL
```
http://localhost:8080
```

### Endpoints

#### 1. Get All Animals
**GET** `/animals`

Retrieves all marine animals in the database.

**Response Example:**
```json
[
  {
    "animalId": 1,
    "name": "Blue Whale",
    "description": "The largest animal on Earth",
    "species": "Balaenoptera musculus",
    "habitat": "Open ocean"
  },
  {
    "animalId": 2,
    "name": "Clownfish",
    "description": "Small, colorful reef fish",
    "species": "Amphiprioninae",
    "habitat": "Coral reefs"
  }
]
```

---

#### 2. Get Animal by ID
**GET** `/animals/{id}`

Retrieves a specific marine animal by its ID.

**Path Parameters:**
- `id` (Long) - The unique identifier of the animal

**Success Response (200):**
```json
{
  "animalId": 1,
  "name": "Blue Whale",
  "description": "The largest animal on Earth",
  "species": "Balaenoptera musculus",
  "habitat": "Open ocean"
}
```

**Error Response (404):**
```
Not Found
```

---

#### 3. Create New Animal
**POST** `/animals`

Creates a new marine animal entry.

**Request Body:**
```json
{
  "name": "Great White Shark",
  "description": "Large predatory shark",
  "species": "Carcharodon carcharias",
  "habitat": "Coastal waters"
}
```

**Success Response (201):**
```json
{
  "animalId": 3,
  "name": "Great White Shark",
  "description": "Large predatory shark",
  "species": "Carcharodon carcharias",
  "habitat": "Coastal waters"
}
```

**Note:** Do not include `animalId` in the request body - it will be auto-generated.

---

#### 4. Update Animal
**PUT** `/animals/{id}`

Updates an existing marine animal.

**Path Parameters:**
- `id` (Long) - The unique identifier of the animal to update

**Request Body:**
```json
{
  "name": "Great White Shark",
  "description": "Large predatory shark found in cool coastal waters",
  "species": "Carcharodon carcharias",
  "habitat": "Cool coastal waters worldwide"
}
```

**Success Response (200):**
```json
{
  "animalId": 3,
  "name": "Great White Shark",
  "description": "Large predatory shark found in cool coastal waters",
  "species": "Carcharodon carcharias",
  "habitat": "Cool coastal waters worldwide"
}
```

**Error Response (404):**
```
Not Found
```

---

#### 5. Delete Animal
**DELETE** `/animals/{id}`

Deletes a marine animal by its ID.

**Path Parameters:**
- `id` (Long) - The unique identifier of the animal to delete

**Success Response (204):**
```
No Content
```

---

#### 6. Get Animals by Category (Species)
**GET** `/animals/category/{category}`

Retrieves all marine animals of a specific species.

**Path Parameters:**
- `category` (String) - The species to filter by

**Example Request:**
```
GET /animals/category/Balaenoptera musculus
```

**Response Example:**
```json
[
  {
    "animalId": 1,
    "name": "Blue Whale",
    "description": "The largest animal on Earth",
    "species": "Balaenoptera musculus",
    "habitat": "Open ocean"
  }
]
```

---

#### 7. Search Animals by Name
**GET** `/animals/search?name={substring}`

Searches for marine animals whose names contain the specified substring (case-insensitive).

**Query Parameters:**
- `name` (String) - The substring to search for in animal names

**Example Request:**
```
GET /animals/search?name=whale
```

**Response Example:**
```json
[
  {
    "animalId": 1,
    "name": "Blue Whale",
    "description": "The largest animal on Earth",
    "species": "Balaenoptera musculus",
    "habitat": "Open ocean"
  },
  {
    "animalId": 4,
    "name": "Killer Whale",
    "description": "Actually a dolphin, apex predator",
    "species": "Orcinus orca",
    "habitat": "All oceans"
  }
]
```

---

## Data Model

### MarineAnimal Entity

| Field | Type | Description | Constraints |
|-------|------|-------------|-------------|
| animalId | Long | Unique identifier | Primary Key, Auto-generated |
| name | String | Common name of the animal | Required |
| description | String | Brief description | Required |
| species | String | Scientific name or species | Optional |
| habitat | String | Where the animal lives | Optional |

---

## Technology Stack

- **Framework:** Spring Boot 3.x
- **Language:** Java 17
- **Database:** PostgreSQL (Neon Tech)
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Gradle
- **API Testing:** Thunder Client / Postman

---

## Project Structure

```
src/main/java/com/example/oceanlifeapi/
├── OceanLifeApiApplication.java          # Main application class
├── controller/
│   └── MarineAnimalController.java       # REST endpoints
├── model/
│   └── MarineAnimal.java                 # Entity class
├── repository/
│   └── MarineAnimalRepository.java       # Data access layer
└── service/
    └── MarineAnimalService.java          # Business logic layer
```

---

## Demo Video

📹 **[Link to Demo Video](YOUR_ONEDRIVE_LINK_HERE)**

*Note: Replace with your OneDrive link after recording your demo*

---

## Testing the API

You can test all endpoints using Thunder Client, Postman, or Insomnia:

1. Start the application
2. Import the following sample requests or create them manually
3. Test each endpoint with valid and invalid data

### Sample Test Data

```json
{
  "name": "Sea Turtle",
  "description": "Ancient marine reptile",
  "species": "Cheloniidae",
  "habitat": "Warm ocean waters"
}
```

```json
{
  "name": "Octopus",
  "description": "Intelligent eight-armed mollusk",
  "species": "Octopoda",
  "habitat": "Rocky ocean floor"
}
```

---

## Error Handling

- **404 Not Found:** Returned when requesting a non-existent animal ID
- **500 Internal Server Error:** Returned for database or server errors

---

## Author

Ahmed Omar - CSC 340 Assignment 3

---

## License

This project is created for educational purposes as part of CSC 340 coursework.