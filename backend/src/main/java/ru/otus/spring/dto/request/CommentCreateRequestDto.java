package ru.otus.spring.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentCreateRequestDto {

    @NotNull
    @Size(min = 10, max = 1000, message
            = "Text must be between 10 and 200 characters")
    private String text;

    @NotNull
    private long taskId;
}