package ru.otus.spring.service;

import ru.otus.spring.dto.request.TaskRequestInsertDto;
import ru.otus.spring.dto.request.TaskRequestUpdateDto;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskFullResponseDto findFullById(long id);

    TaskResponseDto findById(long id);

    List<TaskResponseDto> findAll();

    List<TaskResponseDto> findAllByAssignedToId(Long assignedToId);

    List<TaskResponseDto> findAllByCreatedById(Long createdById);

    List<TaskResponseDto> findAllByUpdatedById(Long updatedById);

    List<TaskResponseDto> findAllTasksByPriority(Long priorityId);

    List<TaskResponseDto> findAllTasksByStatus(Long statusId);

    TaskResponseDto insert(TaskRequestInsertDto taskRequestInsertDto);

    TaskResponseDto update(long id, TaskRequestUpdateDto taskRequestUpdateDto);

    void deleteById(long id);
}
