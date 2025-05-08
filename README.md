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
## 성공 테스트

<details><summary>일정 생성
</summary>
  
  ![Image](https://github.com/user-attachments/assets/4b2f9a31-b717-4f2b-8981-c0c8cf9ea45d)
</details>

<details><summary>일정 전체 조회
</summary>
  
  ![Image](https://github.com/user-attachments/assets/ceba8f2b-9b99-419e-a6af-f6373e549d8d)
</details>

<details><summary>일정 단건 조회
</summary>
  
  ![Image](https://github.com/user-attachments/assets/5aa352f2-cc38-46a7-a8d1-6dcabc4b9291)
  
  ![Image](https://github.com/user-attachments/assets/4f7d4102-06da-4a83-8e5c-a2c3b1b0f752)
</details>

<details><summary>일정 수정
</summary>
  
  ![Image](https://github.com/user-attachments/assets/ffd13071-6487-481e-9df0-6e9638720f0b)

</details>

<details><summary>일정 삭제
</summary>
  
  ![Image](https://github.com/user-attachments/assets/20e51a15-1d7c-4afa-9c59-ade6088594e3)
</details>

##실패 테스트

<details><summary>일정 생성
</summary>
  
</details>


