# Solar Hub

REST API for managing and monitoring solar energy installations. Work in progress.

> **Status:** under development. Phase 1 complete (backend foundation, user registration and authentication). Next: CRUD for installations and panels.

🇧🇷 [Leia em português](README.pt-BR.md)

## Tech stack

- Java 25 and Spring Boot
- Spring Security with JWT authentication
- Spring Data JPA / Hibernate
- PostgreSQL
- Flyway (database migrations)
- Docker Compose (local database)

## What's implemented

- User registration with input validation
- Login with JWT access token generation
- Protected endpoint that returns the authenticated user
- Database schema versioned with Flyway (Hibernate only validates it)

## Getting started

**Prerequisites:** Java 25 and Docker.

1. Clone the repository:

   ```bash
   git clone https://github.com/fgsilvadev/solar-hub.git
   cd solar-hub
   ```

2. Start the database:

   ```bash
   docker compose up -d
   ```

3. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows: `mvnw.cmd spring-boot:run`

The API will be available at `http://localhost:8080`.

### Environment variables (optional)

The defaults are for local development only. In any other environment, set your own values:

| Variable | Description | Default (dev) |
|---|---|---|
| `DB_HOST` | PostgreSQL host | `localhost` |
| `DB_PORT` | PostgreSQL port | `5432` |
| `DB_NAME` | Database name | `solarhub` |
| `DB_USER` | Database user | `solarhub` |
| `DB_PASSWORD` | Database password | `solarhub` |
| `JWT_SECRET` | JWT signing key (at least 32 characters) | development value |

## API endpoints

| Method | Path | Auth | Description |
|---|---|---|---|
| `POST` | `/api/auth/register` | No | Register a new user |
| `POST` | `/api/auth/login` | No | Log in and receive an access token |
| `GET` | `/api/users/me` | Yes | Get the authenticated user |

## Usage examples

**Register a user**

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{ "name": "Jane Doe", "email": "jane@example.com", "password": "changeme123" }'
```

Example response:

```json
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane@example.com",
  "role": "CUSTOMER",
  "createdAt": "2026-11-07T14:30:00Z"
}
```

**Log in**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{ "email": "jane@example.com", "password": "changeme123" }'
```

Example response:

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer"
}
```

**Get the authenticated user**

Use the `accessToken` returned by the login:

```bash
curl http://localhost:8080/api/users/me \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN"
```

## Roadmap

- [x] Phase 1: backend foundation, users and JWT authentication
- [ ] Phase 2: CRUD for installations and panels, scoped to the authenticated user
- [ ] Generation readings
- [ ] Expected vs. actual generation comparison, with low-performance alerts
- [ ] Automated tests, API documentation (OpenAPI) and deployment

## Author

Felipe Gomes da Silva · [LinkedIn](https://www.linkedin.com/in/fgsilvadev/)