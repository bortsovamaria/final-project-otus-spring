package ru.otus.spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.spring.domain.Priority;
import ru.otus.spring.domain.Status;
import ru.otus.spring.domain.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TaskResponseDto {
    private long id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private User createdBy;

    private User updatedBy;

    private User assignedTo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
