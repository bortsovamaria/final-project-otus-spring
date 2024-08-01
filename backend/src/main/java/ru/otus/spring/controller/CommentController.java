package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable long id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Получить список комментариев по ID задачи")
    @GetMapping("/api/comments/task")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByTaskId(@RequestParam long id) {
        return new ResponseEntity<>(commentService.findByTaskId(id), HttpStatus.OK);
    }

    @Operation(summary = "Добавить комментарий")
    @PostMapping("/api/comments")
    public ResponseEntity<CommentResponseDto> insertComment(
            @RequestBody @Valid CommentCreateRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.insert(commentRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Редактировать комментарий по ID")
    @PatchMapping("/api/comments")
    public ResponseEntity<CommentResponseDto> updateComment(
            @RequestBody @Valid CommentUpdateRequestDto commentUpdateRequestDto) {
        return new ResponseEntity<>(commentService.update(commentUpdateRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "Удалить комментарий по ID")
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable long id) {
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
