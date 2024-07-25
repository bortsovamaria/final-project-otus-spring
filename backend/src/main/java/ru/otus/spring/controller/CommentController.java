package ru.otus.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.request.CommentCreateRequestDto;
import ru.otus.spring.dto.request.CommentUpdateRequestDto;
import ru.otus.spring.dto.response.CommentResponseDto;
import ru.otus.spring.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/comments/{id}")
    public CommentResponseDto getCommentById(@PathVariable long id) {
        return commentService.findById(id);
    }

    @GetMapping("/api/comments/task")
    public List<CommentResponseDto> getCommentsByTaskId(@RequestParam long id) {
        return commentService.findByTaskId(id);
    }

    @PostMapping("/api/comments")
    public CommentResponseDto insertComment(@RequestBody @Valid CommentCreateRequestDto commentRequestDto) {
        return commentService.insert(commentRequestDto);
    }

    @PatchMapping("/api/comments")
    public CommentResponseDto updateComment(@RequestBody @Valid CommentUpdateRequestDto commentUpdateRequestDto) {
        return commentService.update(commentUpdateRequestDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public void deleteCommentById(@PathVariable long id) {
        commentService.deleteById(id);
    }
}
