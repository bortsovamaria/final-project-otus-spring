package ru.otus.spring.dto.request;

import lombok.Getter;

@Getter
public class TaskRequestUpdateDto {
    private String title;

    private String description;

    private Long updatedBy;

    private Long assignedTo;
}
