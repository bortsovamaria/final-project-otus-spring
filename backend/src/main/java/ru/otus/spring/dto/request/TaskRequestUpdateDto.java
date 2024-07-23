package ru.otus.spring.dto.request;

import lombok.Getter;
import ru.otus.spring.domain.Priority;
import ru.otus.spring.domain.Status;

@Getter
public class TaskRequestUpdateDto {
    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private Long updatedBy;

    private Long assignedTo;
}
