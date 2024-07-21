package ru.otus.spring.controller;

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
    public TaskFullResponseDto getTaskById(@PathVariable long id) {
        return taskService.findById(id);
    }

    @PostMapping("/api/tasks")
    public TaskResponseDto insertTask(@RequestBody TaskRequestInsertDto taskRequestInsertDto) {
        return taskService.insert(taskRequestInsertDto);
    }

    @PatchMapping("/api/tasks/{id}")
    public TaskResponseDto updateTask(@PathVariable long id, @RequestBody TaskRequestUpdateDto taskRequestUpdateDto) {
        return taskService.update(id, taskRequestUpdateDto);
    }

    @DeleteMapping("/api/tasks/{id}")
    public void deleteTaskById(@PathVariable long id) {
        taskService.deleteById(id);
    }
}
