# Sample Application with Spring and Kotlin
____
- Kotlin
- Sprint Boot
- Gradle
- Postgres
- Flyway
____

A simple REST API example using Kotlin.<br/>
#### The app defines following CRUD APIs:<br/>

**Tasks:**
```
GET /v1/tasks 
GET /v1/tasks/{id}
POST /v1/tasks
PUT /v1/tasks/{id}
DELETE /v1/tasks/{id}
```

**Users:**
```
GET /v1/users 
GET /v1/users/{id}
```

## Integrations

## Run with gradle
```./gradlew bootRun```<br/>

By default the local port is 8080: http://localhost:8080/<br/>
You can also take a look at all controllers and endpoints in Swagger UI at:
http://127.0.0.1:8080/swagger-ui/

# Author
[Saule Tussupbekova](https://www.linkedin.com/in/saule-tussupbekova/)

