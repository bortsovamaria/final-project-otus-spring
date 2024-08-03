package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Priority;
import ru.otus.spring.domain.Status;
import ru.otus.spring.domain.Task;
import ru.otus.spring.domain.User;
import ru.otus.spring.dto.mapper.TaskMapper;
import ru.otus.spring.dto.request.TaskRequestInsertDto;
import ru.otus.spring.dto.request.TaskRequestUpdateDto;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.repository.PriorityRepository;
import ru.otus.spring.repository.StatusRepository;
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

    private final StatusRepository statusRepository;

    private final PriorityRepository priorityRepository;

    @Override
    public TaskFullResponseDto findFullById(long id) {
        return taskRepository.findById(id)
                .map(mapper::toFullDto)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public TaskResponseDto findById(long id) {
        return taskRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public boolean existById(long id) {
        return taskRepository.existsById(id);
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
        Status status = statusRepository.findById(taskRequestInsertDto.getStatus())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Status with id " + taskRequestInsertDto.getStatus() + " not found"));
        Priority priority = priorityRepository.findById(taskRequestInsertDto.getPriority())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Priority with id " + taskRequestInsertDto.getStatus() + " not found"));
        Task task = createNewTask(taskRequestInsertDto, currentUser, assignedTo, status, priority);
        return mapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponseDto update(long id, TaskRequestUpdateDto taskRequestUpdateDto) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Task task = updateTask(id, taskRequestUpdateDto, authentication, taskOpt);

        return mapper.toDto(taskRepository.save(task));

    }

    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponseDto> findAllByAssignedToId(Long assignedToId) {
        User assignedTo = userService.findById(assignedToId);
        return taskRepository.findByAssignedTo(assignedTo)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<TaskResponseDto> findAllByCreatedById(Long createdById) {
        User assignedTo = userService.findById(createdById);
        return taskRepository.findByCreatedBy(assignedTo)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<TaskResponseDto> findAllByUpdatedById(Long updatedById) {
        User updatedBy = userService.findById(updatedById);
        return taskRepository.findByUpdatedBy(updatedBy)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<TaskResponseDto> findAllTasksByPriority(Long priorityId) {
        Priority priority = priorityRepository.findById(priorityId).orElseThrow();
        return taskRepository.findTasksByPriority(priority)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<TaskResponseDto> findAllTasksByStatus(Long id) {
        Status status = statusRepository.findById(id).orElseThrow();
        return taskRepository.findTasksByStatus(status)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    private static Task createNewTask(TaskRequestInsertDto taskRequestInsertDto,
                                      User currentUser,
                                      User assignedTo,
                                      Status status,
                                      Priority priority) {
        return Task.builder()
                .title(taskRequestInsertDto.getTitle())
                .description(taskRequestInsertDto.getDescription())
                .createdBy(currentUser)
                .updatedBy(currentUser)
                .assignedTo(assignedTo)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(status)
                .priority(priority)
                .build();
    }

    private Task updateTask(Long id, TaskRequestUpdateDto taskRequestUpdateDto,
                            Authentication authentication,
                            Optional<Task> taskOpt) {
        if (taskOpt.isPresent()) {
            User currentUser = userService.findByUsername(authentication.getName());
            Task task = taskOpt.get();
            updateFields(taskRequestUpdateDto, task, currentUser);
            return task;
        }
        throw new IllegalArgumentException("Task not found with id = " + id);
    }

    private void updateFields(TaskRequestUpdateDto taskRequestUpdateDto,
                              Task task, User currentUser) {

        User assignedTo = userService.findById(taskRequestUpdateDto.getAssignedTo());
        Status status = getStatus(taskRequestUpdateDto);
        Priority priority = getPriority(taskRequestUpdateDto);
        if (!isEmpty(taskRequestUpdateDto.getTitle())) {
            task.setTitle(taskRequestUpdateDto.getTitle());
        }
        if (!isEmpty(taskRequestUpdateDto.getDescription())) {
            task.setDescription(taskRequestUpdateDto.getDescription());
        }
        if (!isEmpty(taskRequestUpdateDto.getAssignedTo())) {
            task.setAssignedTo(assignedTo);
        }
        if (!isEmpty(taskRequestUpdateDto.getStatus())) {
            task.setStatus(status);
        }
        if (!isEmpty(taskRequestUpdateDto.getPriority())) {
            task.setPriority(priority);
        }
        task.setUpdatedBy(currentUser);
        task.setUpdatedAt(LocalDateTime.now());
    }

    private Priority getPriority(TaskRequestUpdateDto taskRequestUpdateDto) {
        return priorityRepository.findById(taskRequestUpdateDto.getPriority().getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Priority with id " + taskRequestUpdateDto.getStatus() + " not found"));
    }

    private Status getStatus(TaskRequestUpdateDto taskRequestUpdateDto) {
        return statusRepository.findById(taskRequestUpdateDto.getStatus().getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Status with id " + taskRequestUpdateDto.getStatus() + " not found"));
    }
}
