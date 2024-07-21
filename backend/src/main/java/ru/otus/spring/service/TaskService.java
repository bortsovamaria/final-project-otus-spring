package ru.otus.spring.service;

import ru.otus.spring.dto.request.TaskRequestInsertDto;
import ru.otus.spring.dto.request.TaskRequestUpdateDto;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskFullResponseDto findById(long id);

    List<TaskResponseDto> findAll();

    TaskResponseDto insert(TaskRequestInsertDto taskRequestInsertDto);

    TaskResponseDto update(long id, TaskRequestUpdateDto taskRequestUpdateDto);

    void deleteById(long id);
}
