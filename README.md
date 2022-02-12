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
GET /v1/tasks get all tasks
GET /v1/tasks/{id} get task by id
POST /v1/tasks create task
PUT /v1/tasks/{id} update task by id
DELETE /v1/tasks/{id} delete task by id
```

**Users:**
```
GET /v1/users get all users
GET /v1/users/{id} get user by id
```

## Integrations
userblock.host=http://localhost:8040 [UserBlockApi](https://github.com/SauleQA/SpringUserBlockApi)
UserBlockApi must be running on the port 8040

## DB
Create database "testtask" with username=postgres and password=postgres

## Run with gradle
```./gradlew bootRun```<br/>

By default the local port is 8080: http://localhost:8080/<br/>
You can also take a look at all controllers and endpoints in Swagger UI at:
http://127.0.0.1:8080/swagger-ui/

## Tests
RestAssured API tests you can find [here](https://github.com/SauleQA/RestAssuredTests)

# Author
[Saule Tussupbekova](https://www.linkedin.com/in/saule-tussupbekova/)

