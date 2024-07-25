package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;
import ru.otus.spring.dto.request.TaskRequestInsertDto;
import ru.otus.spring.dto.request.TaskRequestUpdateDto;
import ru.otus.spring.service.TaskService;

import java.util.List;

@Tag(name = "Задачи")
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Получить весь список задач")
    @GetMapping("/api/tasks")
    public List<TaskResponseDto> getAllTasks() {
        return taskService.findAll();
    }

    @Operation(summary = "Получить задачу по ID")
    @GetMapping("/api/tasks/{id}")
    public TaskFullResponseDto getTaskById(@PathVariable @Min(1) Long id) {
        return taskService.findById(id);
    }

    @Operation(summary = "Добавить комментарий")
    @PostMapping("/api/tasks")
    public TaskResponseDto insertTask(@Valid @RequestBody TaskRequestInsertDto taskRequestInsertDto) {
        return taskService.insert(taskRequestInsertDto);
    }

    @Operation(summary = "Редактировать задачу по ID")
    @PatchMapping("/api/tasks/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestUpdateDto taskRequestUpdateDto) {
        return taskService.update(id, taskRequestUpdateDto);
    }

    @Operation(summary = "Удалить задачу по ID")
    @DeleteMapping("/api/tasks/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    @Operation(summary = "Получить список задач по ID статуса")
    @GetMapping("/api/tasks/assigned")
    public List<TaskResponseDto> getAllTasksByAssignedTo(@RequestParam @Min(1) Long id) {
        return taskService.findAllByAssignedToId(id);
    }

    @Operation(summary = "Получить список задач по ID пользователя, создавшего задачи")
    @GetMapping("/api/tasks/createdby")
    public List<TaskResponseDto> getAllTasksByCreatedBy(@RequestParam @Min(1) Long id) {
        return taskService.findAllByCreatedById(id);
    }

    @Operation(summary = "Получить список задач по ID пользователя, который модифицировал задачи")
    @GetMapping("/api/tasks/updatedby")
    public List<TaskResponseDto> getAllTasksByUpdatedBy(@RequestParam @Min(1) Long id) {
        return taskService.findAllByUpdatedById(id);
    }

    @Operation(summary = "Получить список задач по ID приоритета")
    @GetMapping("/api/tasks/priority")
    public List<TaskResponseDto> getAllTasksByPriority(@RequestParam @Min(1) Long id) {
        return taskService.findAllTasksByPriority(id);
    }

    @Operation(summary = "Получить список задач по ID статуса")
    @GetMapping("/api/tasks/status")
    public List<TaskResponseDto> getAllTasksByStatus(@RequestParam @Min(1) Long id) {
        return taskService.findAllTasksByStatus(id);
    }
}
