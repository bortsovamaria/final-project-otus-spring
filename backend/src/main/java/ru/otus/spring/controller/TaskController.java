package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.dto.request.TaskRequestInsertDto;
import ru.otus.spring.dto.request.TaskRequestUpdateDto;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;
import ru.otus.spring.service.TaskService;

import java.util.List;

@Tag(name = "Задачи")
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Получить весь список задач")
    @GetMapping("/api/tasks")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Получить задачу по ID")
    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<TaskFullResponseDto> getTaskById(@PathVariable @Min(1) Long id) {
        return new ResponseEntity<>(taskService.findFullById(id), HttpStatus.OK);
    }

    @Operation(summary = "Добавить комментарий")
    @PostMapping("/api/tasks")
    public ResponseEntity<TaskResponseDto> insertTask(@Valid @RequestBody TaskRequestInsertDto taskRequestInsertDto) {
        return new ResponseEntity<>(taskService.insert(taskRequestInsertDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Редактировать задачу по ID")
    @PatchMapping("/api/tasks/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id,
                                                      @Valid @RequestBody TaskRequestUpdateDto taskRequestUpdateDto) {
        return new ResponseEntity<>(taskService.update(id, taskRequestUpdateDto), HttpStatus.OK);
    }

    @Operation(summary = "Удалить задачу по ID")
    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Получить список задач по ID статуса")
    @GetMapping("/api/tasks/assigned")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByAssignedTo(@RequestParam @Min(1) Long id) {
        return new ResponseEntity<>(taskService.findAllByAssignedToId(id), HttpStatus.OK);
    }

    @Operation(summary = "Получить список задач по ID пользователя, создавшего задачи")
    @GetMapping("/api/tasks/createdby")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByCreatedBy(@RequestParam @Min(1) Long id) {
        return new ResponseEntity<>(taskService.findAllByCreatedById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получить список задач по ID пользователя, который модифицировал задачи")
    @GetMapping("/api/tasks/updatedby")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByUpdatedBy(@RequestParam @Min(1) Long id) {
        return new ResponseEntity<>(taskService.findAllByUpdatedById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получить список задач по ID приоритета")
    @GetMapping("/api/tasks/priority")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByPriority(@RequestParam @Min(1) Long id) {
        return new ResponseEntity<>(taskService.findAllTasksByPriority(id), HttpStatus.OK);
    }

    @Operation(summary = "Получить список задач по ID статуса")
    @GetMapping("/api/tasks/status")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByStatus(@RequestParam @Min(1) Long id) {
        return new ResponseEntity<>(taskService.findAllTasksByStatus(id), HttpStatus.OK);
    }
}
