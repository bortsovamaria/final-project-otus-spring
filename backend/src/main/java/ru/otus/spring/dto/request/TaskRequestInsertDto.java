package ru.otus.spring.dto.request;

import lombok.Getter;

@Getter
public class TaskRequestInsertDto {
    private String title;

    private String description;

    private Long status;

    private Long priority;

    private Long createdBy;

    private Long updatedBy;

    private Long assignedTo;

}