package ru.otus.spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.otus.spring.domain.Priority;
import ru.otus.spring.domain.Status;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Getter
public class TaskResponseDto {
    private long id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private UserDto createdBy;

    private UserDto updatedBy;

    private UserDto assignedTo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
