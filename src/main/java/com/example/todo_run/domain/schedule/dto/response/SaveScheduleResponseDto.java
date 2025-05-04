package com.example.todo_run.domain.schedule.dto.response;

import com.example.todo_run.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaveScheduleResponseDto {

    private final Long id;
    private final Long writerId;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;

    private SaveScheduleResponseDto(Long id, Long writerId, String title, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }


    public static SaveScheduleResponseDto from(Schedule schedule) {
        return new SaveScheduleResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt()
        );
    }

}
