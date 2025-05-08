# 일정 관리 프로젝트

Spring Boot를 이용한 일정관리 프로젝트 입니다.

## Badges

![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)


## 프로젝트 구조

````

📦 com.example.todo_run
├─ common
│   ├─ entity
│   │  └─ BaseEntity
│   ├─ exception
│   │  ├─ BaseException
│   │  ├─ ErrorCode
│   │  ├─ ExceptionResponse
│   │  └─ GlobalExceptionHandler
│   └─ response
│      ├─ ApiResponse
│      └─ MessageRespon
├─ domain
│  ├─ comment
│  │  ├─ controller
│  │  │  └─ CommentController
│  │  ├─ service
│  │  │  ├─ CommentService
│  │  │  └─ CommentServiceImpl
│  │  ├─ repository
│  │  │  └─ CommentRepository
│  │  ├─ dto
│  │  │  ├─ request
│  │  │  │  ├─ SaveCommentRequestDto
│  │  │  │  └─ UpdateCommentRequestDto
│  │  │  └─ response
│  │  │     ├─ CommentResponseDto
│  │  │     └─ SaveCommentResponseDto
│  │  ├─ entity
│  │  │  └─ Comment
│  │  └─ exception
│  │     ├─ CommentError
│  │     └─ CommentException
│  ├─ inComment
│  │  ├─ controller
│  │  ├─ service
│  │  ├─ repository
│  │  ├─ dto
│  │  │  ├─ request
│  │  │  └─ response
│  │  ├─ entity
│  │  └─ exception
│  └─ schedule
│     ├─ controller
│     │  └─ ScheduleController
│     ├─ service
│     │  ├─ ScheduleService
│     │  ├─ ScheduleServiceImpl
│     │  ├─ ScheduleReadService
│     │  └─ ScheduleReadServiceImpl
│     ├─ repository
│     │  └─ ScheduleRepository
│     ├─ dto
│     │  ├─ request
│     │  │  ├─ SaveScheduleRequestDto
│     │  │  └─ UpdateScheduleRequestDto
│     │  └─ response
│     │     ├─ SaveScheduleResponseDto
│     │     ├─ ScheduleCommentListResponseDto
│     │     ├─ ScheduleResponseDto
│     │     └─ UpdateScheduleResponseDto
│     ├─ entity
│     │  └─ Schedule
│     └─ exception
│        ├─ ScheduleError
│        └─ ScheduleException
├─ todo.sql
└─ TodoRunApplication

````
