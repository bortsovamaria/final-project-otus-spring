package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.request.CommentCreateRequestDto;
import ru.otus.spring.dto.request.CommentUpdateRequestDto;
import ru.otus.spring.dto.response.CommentResponseDto;
import ru.otus.spring.service.CommentService;

import java.util.List;

@Tag(name = "Комментарии")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Получить комментарий по ID")
    @GetMapping("/api/comments/{id}")
    public CommentResponseDto getCommentById(@PathVariable long id) {
        return commentService.findById(id);
    }

    @Operation(summary = "Получить список комментариев по ID задачи")
    @GetMapping("/api/comments/task")
    public List<CommentResponseDto> getCommentsByTaskId(@RequestParam long id) {
        return commentService.findByTaskId(id);
    }

    @Operation(summary = "Добавить комментарий")
    @PostMapping("/api/comments")
    public CommentResponseDto insertComment(@RequestBody @Valid CommentCreateRequestDto commentRequestDto) {
        return commentService.insert(commentRequestDto);
    }

    @Operation(summary = "Редактировать комментарий по ID")
    @PatchMapping("/api/comments")
    public CommentResponseDto updateComment(@RequestBody @Valid CommentUpdateRequestDto commentUpdateRequestDto) {
        return commentService.update(commentUpdateRequestDto);
    }

    @Operation(summary = "Удалить комментарий по ID")
    @DeleteMapping("/api/comments/{id}")
    public void deleteCommentById(@PathVariable long id) {
        commentService.deleteById(id);
    }
}
