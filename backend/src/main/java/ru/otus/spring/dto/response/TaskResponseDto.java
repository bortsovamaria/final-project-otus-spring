package ru.otus.spring.dto.response;

import lombok.Getter;
import ru.otus.spring.domain.User;

import java.time.LocalDateTime;

@Getter
public class TaskResponseDto {
    private long id;

    private String title;

    private String description;

    private User createdBy;

    private User updatedBy;

    private User assignedTo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
