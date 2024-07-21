package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.mapper.CommentMapper;
import ru.otus.spring.dto.request.CommentRequestDto;
import ru.otus.spring.dto.response.CommentResponseDto;
import ru.otus.spring.dto.request.TaskRequestInsertDto;
import ru.otus.spring.dto.request.TaskRequestUpdateDto;
import ru.otus.spring.dto.response.TaskResponseDto;
import ru.otus.spring.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/comments/{id}")
    public CommentResponseDto getCommentById(@PathVariable long id) {
        return commentService.findById(id).orElseThrow();
    }

    @GetMapping("/api/comments")
    public List<CommentResponseDto> getCommentsByTaskId(@RequestParam long taskId) {
        return commentService.findByTaskId(taskId);
    }

    @PostMapping("/api/comments")
    public CommentResponseDto insertComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.insert(commentRequestDto);
    }

    @PatchMapping("/api/comments")
    public CommentResponseDto updateComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.update(commentRequestDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public void deleteCommentById(@PathVariable long id) {
        commentService.deleteById(id);
    }
}
