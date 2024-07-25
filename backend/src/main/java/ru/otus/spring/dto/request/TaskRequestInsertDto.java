package ru.otus.spring.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TaskRequestInsertDto {
    @NotNull
    private String title;

    private String description;

    @NotNull
    private Long status;

    @NotNull
    private Long priority;

    private Long assignedTo;

}