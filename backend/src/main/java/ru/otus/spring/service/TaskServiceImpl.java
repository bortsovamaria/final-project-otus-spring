package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Task;
import ru.otus.spring.domain.User;
import ru.otus.spring.dto.mapper.TaskMapper;
import ru.otus.spring.dto.request.TaskRequestInsertDto;
import ru.otus.spring.dto.request.TaskRequestUpdateDto;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;
import ru.otus.spring.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final UserService userService;

    private final TaskMapper mapper;

    @Override
    public TaskFullResponseDto findById(long id) {
        return taskRepository.findById(id)
                .map(mapper::toFullDto)
                .orElseThrow();
    }

    @Override
    public List<TaskResponseDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public TaskResponseDto insert(TaskRequestInsertDto taskRequestInsertDto) {
        User currentUser = userService.getCurrentUser();
        User assignedTo = userService.findById(taskRequestInsertDto.getAssignedTo());
        Task task = Task.builder()
                .title(taskRequestInsertDto.getTitle())
                .description(taskRequestInsertDto.getDescription())
                .createdBy(currentUser)
                .updatedBy(currentUser)
                .assignedTo(assignedTo)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return mapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponseDto update(long id, TaskRequestUpdateDto taskRequestUpdateDto) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = userService.findByUsername(authentication.getName());
            User assignedTo = userService.findById(taskRequestUpdateDto.getAssignedTo());
            Task task = taskOpt.get();
            if (!isEmpty(taskRequestUpdateDto.getTitle())) {
                task.setTitle(taskRequestUpdateDto.getTitle());
            }
            if (!isEmpty(taskRequestUpdateDto.getDescription())) {
                task.setDescription(taskRequestUpdateDto.getDescription());
            }
            if (!isEmpty(taskRequestUpdateDto.getAssignedTo())) {
                task.setAssignedTo(assignedTo);
            }
            task.setUpdatedBy(currentUser);
            task.setUpdatedAt(LocalDateTime.now());

            return mapper.toDto(taskRepository.save(task));
        }
        throw new IllegalArgumentException("Task not found with id = " + id);
    }

    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }
}
