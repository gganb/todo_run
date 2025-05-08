# 일정 관리 프로젝트

Spring Boot를 이용한 일정관리 프로젝트 입니다.

## Badges

![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

## 목차

1. [프로젝트 구조](#프로젝트-구조)
2. [ERD](#erd)
3. [SQL](#sql)
4. [API 명세서](#api-명세서)
   - [Schedule API](#schedule-api)
   - [Error Response - Schedule](#error-response---schedule)
   - [Comment API](#comment-api)
   - [Error Response - Comment](#error-response---comment)
5. [API 테스트](#api-테스트)
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
<br>

## ERD

<details><summary>ERD
</summary>

![Image](https://github.com/user-attachments/assets/6654f122-5faa-47ea-b532-57a03d48233a)
</details>

<br>

## SQL

> todo_run.sql

```
create table schedules
(
    id         BIGINT auto_increment primary key,
    writer_id  BIGINT       NOT NULL,
    title      VARCHAR(100) NOT NULL,
    contents   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
create table comments
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT       NOT NULL,
    writer_id   BIGINT       NOT NULL,
    contents    VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    foreign key (schedule_id) references schedules (id)
);

create table in_comments
(
    id                BIGINT AUTO_INCREMENT primary key,
    schedule_id       BIGINT       NOT NULL, /* 일정 id */
    writer_id         BIGINT       NOT NULL, /* 작성자 id */
    parent_comment_id BIGINT       NOT NULL, /* 부모 댓글 id */
    content           VARCHAR(255) NOT NULL,
    created_at        TIMESTAMP,
    updated_at        TIMESTAMP,
    foreign key (schedule_id) references schedules (id),
    foreign key (parent_comment_id) references comments (id)

);

```

<br>

## API 명세서

- ### Schedule API

| 기능             | Method | URL                             | Path / Param              | RequestBody                                                              | ResponseBody                                                                                                              | Error                        |
|----------------|--------|----------------------------------|----------------------------|---------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|-----------------------------|
| 일정 생성          | POST   | /api/schedules                   | 없음                       | { "writerId": 1, "title": "일정 제목", "contents": "일정 내용" }         | { "message": "일정이 생성되었습니다.",<br> "data": { "id": 1, "writerId": 1, "title": "일정 제목",<br> "contents": "일정 내용", "createdAt": "...", "updatedAt": "..." } } | 400 Bad Request              |
| 전체 일정 조회       | GET    | /api/schedules                   | writerId=1 (Query Param)   | 없음                                                                     | { "message": "일정을 작성자 기준으로 조회합니다.",<br> "data": [ { "id": 1, "writerId": 1, "title": "제목",<br> "commentCount": 3, "contents": "내용", "createdAt": "...", "updatedAt": "..." } ] } | 404 NOT FOUND                |
| 단건 일정 조회       | GET    | /api/schedules/{scheduleId}      | scheduleId = 2             | 없음                                                                     | { "message": "일정을 단건 조회합니다.",<br> "data": { "scheduleId": 9, "scheduleWriterId": 2, "title": "제목", "contents": "123",<br> "commentsList": [ { "id": 33, "writerId": 4, "scheduleId": 9, "contents": "댓글", "createdAt": "...", "updatedAt": "..." } ] } } | 404 NOT FOUND                |
| 일정 수정          | PUT    | /api/schedules/{scheduleId}      | scheduleId = 2             | { "scheduleWriterId": 1, "title": "제목 수정", "contents": "내용 수정" } | { "message": "일정이 수정되었습니다.",<br> "data": { "id": 1, "title": "제목 수정", "contents": "내용 수정", "createdAt": "...", "updatedAt": "..." } } | 400 Bad Request<br>404 NOT FOUND |
| 일정 삭제          | DELETE | /api/schedules/{scheduleId}      | writerId=1, scheduleId=2   | 없음                                                                     | { "message": "일정이 삭제되었습니다." }                                                                                     | 404 NOT FOUND                |

<br>

- ### Error Response - Schedule

| 기능               | Error Name                     | Error Number | Error Code        | Error Response                                                                                   |
|------------------|--------------------------------|--------------|-------------------|--------------------------------------------------------------------------------------------------|
| 조회<br>수정<br>삭제   | SCHEDULE_NOT_FOUND            | S01          | 404 Not Found     | {<br>  "status": 404,<br>  "code": "S01",<br>  "message": "일정을 찾을 수 없습니다." <br>} |
| 일정 수정            | SCHEDULE_MODIFY_NOT_ALLOWED    | S02          | 400 Bad Request   | {<br>  "status": 400,<br>  "code": "S02",<br>  "message": "본인의 일정만 수정할 수 있습니다." <br>} |
| 일정 삭제            | SCHEDULE_DELETE_NOT_ALLOWED    | S03          | 400 Bad Request   | {<br>  "status": 400,<br>  "code": "S03",<br>  "message": "본인의 일정만 삭제할 수 있습니다." <br>} |
| 일정 수정            | SCHEDULE_UPDATE_NO_FIELDS      | S04          | 400 Bad Request   | {<br>  "status": 400,<br>  "code": "S04",<br>  "message": "수정할 제목 또는 내용을 하나 이상 입력해주세요." <br>} |
| 일정 수정            | TITLE_EMPTY                    | S05          | 400 Bad Request   | {<br>  "status": 400,<br>  "code": "S05",<br>  "message": "제목은 공백이 될 수 없습니다." <br>} |
| 일정 수정            | UPDATE_FAILED                  | S05          | 400 Bad Request   | {<br>  "code": "S05",<br>  "status": "400 Bad Request",<br>  "message": "변경할 내용을 입력해주세요." <br>} |

<br>

- ### Comment API

| 기능                | Method | URL                                     | Path / Param                   | RequestBody                                                      | ResponseBody                                                                                                                  | Error                          |
|-------------------|--------|------------------------------------------|--------------------------------|-------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|-------------------------------|
| 댓글 작성             | POST   | /api/schedules/{scheduleId}/comments     | scheduleId = 1                 | { "writerId": 2, "contents": "댓글1" }                           | { "message": "댓글이 저장되었습니다.",<br> "data": { "id": 1, "writerId": 2, "scheduleId": 1, "contents": "댓글1", "createdAt": "..." } } | 400 Bad Request<br>404 NOT FOUND |
| 전체 댓글 조회          | GET    | /api/schedules/{scheduleId}/comments     | scheduleId = 1                 | 없음                                                              | { "message": "1번 일정의 모든 댓글을 조회합니다.",<br> "data": [ { "id": 3, "writerId": 2, "scheduleId": 1, "contents": "댓글2", "createdAt": "...", "updatedAt": "..." } ] } | 404 NOT FOUND                  |
| 단일 댓글 조회          | GET    | /api/comments/{commentId}               | commentId = 2                  | 없음                                                              | { "message": "해당 댓글이 조회되었습니다.",<br> "data": { "id": 1, "writerId": 1, "scheduleId": 2, "contents": "댓글1", "createdAt": "...", "updatedAt": "..." } } | 404 NOT FOUND                  |
| 댓글 수정             | PUT    | /api/comments/{commentId}               | commentId = 2                  | { "writerId": 2, "contents": "수정" }                            | { "message": "댓글이 수정되었습니다.",<br> "data": { "id": 2, "writerId": 2, "scheduleId": 1, "contents": "수정", "createdAt": "...", "updatedAt": "..." } } | 400 Bad Request<br>404 NOT FOUND |
| 댓글 삭제             | DELETE | /api/comments/{commentId}               | commentId = 2<br>writerId = 2  | 없음                                                              | { "message": "댓글이 삭제되었습니다." }                                                                                          | 404 NOT FOUND                  |

<br>

- ### Error Response - Comment

| 기능               | Error Name                     | Error Number | Error Code        | Error Response                                                                                      |
|------------------|----------------------------------|--------------|-------------------|------------------------------------------------------------------------------------------------------|
| 댓글 생성<br>댓글 조회   | USER_NOT_FOUND                  | S01          | 404 Not Found     | {<br>  "code": "S01",<br>  "status": "404 Not Found",<br>  "message": "사용자를 찾을 수 없습니다." <br>} |
| 댓글 조회<br>댓글 삭제   | COMMENTS_NOT_FOUND              | C01          | 404 Not Found     | {<br>  "status": 404,<br>  "code": "C01",<br>  "message": "댓글이 존재하지 않습니다." <br>}              |
| 댓글 수정            | CONTENTS_MODIFY_NOT_ALLOWED     | C02          | 400 Bad Request   | {<br>  "status": 400,<br>  "code": "C02",<br>  "message": "본인이 작성한 댓글만 수정할 수 있습니다!" <br>} |
| 댓글 삭제            | CONTENTS_DELETE_NOT_ALLOWED     | C03          | 400 Bad Request   | {<br>  "status": 400,<br>  "code": "C03",<br>  "message": "본인이 작성한 댓글만 삭제할 수 있습니다!" <br>} |


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

