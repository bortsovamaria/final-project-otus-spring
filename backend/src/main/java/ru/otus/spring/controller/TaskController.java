package ru.otus.spring.controller;

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

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/api/tasks")
    public List<TaskResponseDto> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/api/tasks/{id}")
    public TaskFullResponseDto getTaskById(@PathVariable @Min(1) Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/api/tasks")
    public TaskResponseDto insertTask(@Valid @RequestBody TaskRequestInsertDto taskRequestInsertDto) {
        return taskService.insert(taskRequestInsertDto);
    }

    @PatchMapping("/api/tasks/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestUpdateDto taskRequestUpdateDto) {
        return taskService.update(id, taskRequestUpdateDto);
    }

    @DeleteMapping("/api/tasks/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    @GetMapping("/api/tasks/assigned")
    public List<TaskResponseDto> getAllTasksByAssignedTo(@RequestParam @Min(1) Long id) {
        return taskService.findAllByAssignedToId(id);
    }

    @GetMapping("/api/tasks/createdby")
    public List<TaskResponseDto> getAllTasksByCreatedBy(@RequestParam @Min(1) Long id) {
        return taskService.findAllByCreatedById(id);
    }

    @GetMapping("/api/tasks/updatedby")
    public List<TaskResponseDto> getAllTasksByUpdatedBy(@RequestParam @Min(1) Long id) {
        return taskService.findAllByUpdatedById(id);
    }

    @GetMapping("/api/tasks/priority")
    public List<TaskResponseDto> getAllTasksByPriority(@RequestParam @Min(1) Long id) {
        return taskService.findAllTasksByPriority(id);
    }

    @GetMapping("/api/tasks/status")
    public List<TaskResponseDto> getAllTasksByStatus(@RequestParam @Min(1) Long id) {
        return taskService.findAllTasksByStatus(id);
    }
}
