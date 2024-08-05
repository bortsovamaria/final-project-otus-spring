package ru.otus.spring.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
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