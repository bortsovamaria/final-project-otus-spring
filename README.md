Таск-трекер

Проектная работа курса "Разработчик Spring Framework"
Борцова Мария

Стек: 

    Backend:

    - Spring Boot 3
    - Spring MVC
    - Bean Validation
    - Spring Data JPA
    - PostgreSQL
    - Spring Security 6
    - JWT
    - Liquibase
    - Swagger
    - Actuator  
    - Docker

    Frontend (частично): 

    - React
    - Typescript
    - Redux toolkit
    - Router


Сущности:
    - Task
    - Comment
    - Priority
    - Status
    - Type
    - Attachment
    - Role
    - User

![](D:\code\final-project-otus-spring\architecture_db.PNG)

API:

    Login POST /api/auth/login
    Register POST /api/auth/register

    Get all tasks GET /api/tasks
    Get tasks by assignment user assignment GET /api/tasks
    Get tasks by created user GET /api/tasks
    Get tasks by priority GET /api/tasks
    Get tasks by status GET /api/tasks
    Add task POST /api/tasks
    Update task PUT /api/tasks/{id}
    Delete task DELETE /api/tasks/{id}

    Get attachment GET /api/attachments/{id}

    Get comments by task GET /api/comments/{taskId}
    Update comment PUT /api/tasks/{id}
    Delete comment DELETE /api/tasks/{id}
    

    