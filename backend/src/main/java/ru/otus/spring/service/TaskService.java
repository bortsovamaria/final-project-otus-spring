package ru.otus.spring.service;

import ru.otus.spring.dto.response.TaskResponseDto;
import ru.otus.spring.dto.request.TaskRequestInsertDto;
import ru.otus.spring.dto.request.TaskRequestUpdateDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<TaskResponseDto> findById(long id);

    List<TaskResponseDto> findAll();

    TaskResponseDto insert(TaskRequestInsertDto taskRequestInsertDto);

    TaskResponseDto update(long id, TaskRequestUpdateDto taskRequestUpdateDto);

    void deleteById(long id);
}
