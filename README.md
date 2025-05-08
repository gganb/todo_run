# 일정 관리 프로젝트

Spring Boot를 이용한 일정관리 프로젝트 입니다.

## Badges

![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

## 목차

1. [프로젝트 구조](#프로젝트-구조)
2. [API 테스트](#api-테스트)
   - [성공 테스트 - 일정](#성공-테스트---일정)
   - [실패 테스트 - 일정](#실패-테스트---일정)
   - [성공 테스트 - 댓글](#성공-테스트---댓글)
   - [실패 테스트 - 댓글](#실패-테스트---댓글)

  
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
## API 명세서

- ### 일정 API

  | 기능       | Method | URL                           | Path / Param                 | RequestBody                                                        | ResponseBody                                                                                                                                                                                                                                       | Error                                |
| -------- | ------ | ----------------------------- | ---------------------------- | ------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------ |
| 일정 생성    | POST   | `/api/schedules`              | 없음                           | `{ "writerId": 1, "title": "일정 제목", "contents": "일정 내용" }`         | `{ "message": "일정이 생성되었습니다.", "data": { "id": 1, "writerId": 1, "title": "일정 제목", "contents": "일정 내용", "createdAt": "...", "updatedAt": "..." } }`                                                                                                 | `400 Bad Request`                    |
| 전체 일정 조회 | GET    | `/api/schedules`              | `writerId=1` (Param)         | 없음                                                                 | `{ "message": "일정을 작성자 기준으로 조회합니다.", "data": [ { "id": 1, "writerId": 1, "title": "일정 제목", "commentCount": 3, "contents": "내용", "createdAt": "...", "updatedAt": "..." } ] }`                                                                      | `404 NOT FOUND`                      |
| 단건 일정 조회 | GET    | `/api/schedules/{scheduleId}` | `scheduleId = 2`             | 없음                                                                 | `{ "message": "일정을 단건 조회합니다.", "data": { "scheduleId": 9, "scheduleWriterId": 2, "title": "제목", "contents": "123", "commentsList": [ { "id": 33, "writerId": 4, "scheduleId": 9, "contents": "댓글", "createdAt": "...", "updatedAt": "..." } ] } }` | `404 NOT FOUND`                      |
| 일정 수정    | PUT    | `/api/schedules/{scheduleId}` | `scheduleId = 2`             | `{ "scheduleWriterId": 1, "title": "제목 수정", "contents": "내용 수정" }` | `{ "message": "일정이 수정되었습니다.", "data": { "id": 1, "title": "제목 수정", "contents": "내용 수정", "createdAt": "...", "updatedAt": "..." } }`                                                                                                                | `400 Bad Request`<br>`404 NOT FOUND` |
| 일정 삭제    | DELETE | `/api/schedules/{scheduleId}` | `writerId=1`, `scheduleId=2` | 없음                                                                 | `{ "message": "일정이 삭제되었습니다." }`                                                                                                                                                                                                                    | `404 NOT FOUND`                      |






## API 테스트 

### 성공 테스트  - 일정

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

 ![Image](https://github.com/user-attachments/assets/3210a6e7-957f-4c72-b91a-b05b60b59a90)
</details>

### 실패 테스트 - 일정

<details><summary>일정 생성
</summary>
  
  ![Image](https://github.com/user-attachments/assets/20e51a15-1d7c-4afa-9c59-ade6088594e3)
</details>

<details><summary>일정 전체 조회
</summary>
  
  ![Image](https://github.com/user-attachments/assets/7f37db25-f790-4c08-a8f4-38e71ad004a1)
</details>

<details><summary>일정 단건 조회
</summary>
  
 ![Image](https://github.com/user-attachments/assets/4b07ea69-bd69-4723-a6f4-30ae0fa55b22)
</details>

<details><summary>일정 수정
</summary>
  
  ![Image](https://github.com/user-attachments/assets/9e7f48b4-cfc5-4e8f-aacc-883b5c4f82af)
  ![Image](https://github.com/user-attachments/assets/806b0e61-e538-454b-913a-5ea4fa7f9ed1)
</details>


### 성공 테스트  - 댓글
<details><summary>댓글 생성
</summary>
  
  ![Image](https://github.com/user-attachments/assets/af9d8e69-3373-4d1e-bbbb-20a423869eaa)
</details>

<details><summary>댓글 전체 조회
</summary>

  ![Image](https://github.com/user-attachments/assets/f6443587-0696-4a47-af22-1d1f7cdaea8d)
</details>

<details><summary>댓글 단건 조회
</summary>
  
  ![Image](https://github.com/user-attachments/assets/79205eb1-3bb7-4dc8-8bb9-6a61396099cc) 
</details>

<details><summary>댓글 수정
</summary>

  ![Image](https://github.com/user-attachments/assets/1fb8a62e-2447-4646-979e-27593d49b311)
</details>

<details><summary>댓글 삭제
</summary>

  ![Image](https://github.com/user-attachments/assets/66cde389-1409-478a-944e-55973edf54d4)
</details>



### 실패 테스트 - 댓글
<details><summary>댓글 생성
</summary>
  
![Image](https://github.com/user-attachments/assets/72434c6d-a54a-4ab8-9450-359511bb040c)
</details>

<details><summary>댓글 삭제
</summary>

 ![Image](https://github.com/user-attachments/assets/da863c5a-05bb-4679-9925-d25ef9044199)
</details>

