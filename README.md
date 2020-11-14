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
GET /v1/api/tasks 
GET /v1/api/tasks/{id}
POST /v1/api/tasks
PUT /v1/api/tasks/{id}
DELETE /v1/api/tasks/{id}
```

**Users:**
```
GET /v1/api/users 
GET /v1/api/users/{id}
```


## Run with gradle
```./gradlew bootRun```<br/>

By default the local port is 8080: http://localhost:8080/<br/>
You can also take a look at all controllers and endpoints in Swagger UI at:
http://127.0.0.1:8080/swagger-ui/
