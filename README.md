# user-service-demo

A simple Spring Boot service demonstrating user account management.

## Features

- Create user accounts with username and email validation
- Retrieve users by ID or list all users
- Delete user accounts by ID
- Duplicate username detection

## API

### Create a user

```
POST /users
{
  "username": "alice",
  "email": "alice@example.com"
}
```

Returns the created user with generated ID. Throws `400 Bad Request` if
username or email is blank, or `409 Conflict` if the username already exists.

### Get a user

```
GET /users/{id}
```

Returns the user or `404 Not Found` if no user with the given ID exists.

### List all users

```
GET /users
```

Returns an array of all registered users.

### Delete a user

```
DELETE /users/{id}
```

Returns `204 No Content` on success, `404 Not Found` if the user does not exist.

## Setup

```bash
./mvnw spring-boot:run
```

Requires Java 21.
