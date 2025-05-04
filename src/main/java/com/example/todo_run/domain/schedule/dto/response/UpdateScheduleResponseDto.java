package com.example.todo_run.domain.schedule.dto.response;

import com.example.todo_run.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponseDto {
    private final Long id;
    private final Long writerId;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UpdateScheduleResponseDto(Long id, Long writerId, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public static UpdateScheduleResponseDto from(Schedule schedule) {
        return new UpdateScheduleResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
