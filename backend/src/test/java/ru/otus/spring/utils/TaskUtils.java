package ru.otus.spring.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.domain.Task;
import ru.otus.spring.domain.User;
import ru.otus.spring.dto.response.TaskResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class TaskUtils {

    public static List<Task> getTaskList() {
        return List.of(
                Task.builder()
                        .id(1L)
                        .title("title")
                        .description("descrp")
                        .updatedBy(new User())
                        .createdBy(new User())
                        .createdAt(LocalDateTime.MAX)
                        .updatedAt(LocalDateTime.MAX)
                        .build());
    }

    public static List<TaskResponseDto> getTaskListDto() {
        return List.of(
                TaskResponseDto.builder()
                        .id(1L)
                        .title("title")
                        .description("descr")
                        .build(),
                TaskResponseDto.builder()
                        .id(1L)
                        .title("title")
                        .description("descr")
                        .build());
    }

    public static Task getTask() {
        return Task.builder()
                .id(1L)
                .title("title")
                .description("descrp")
                .build();
    }
}
