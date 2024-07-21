package ru.otus.spring.service;

import ru.otus.spring.dto.request.CommentRequestDto;
import ru.otus.spring.dto.response.CommentResponseDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<CommentResponseDto> findById(long id);

    List<CommentResponseDto> findByTaskId(long taskId);

    CommentResponseDto insert(CommentRequestDto commentRequestDto);

    CommentResponseDto update(CommentRequestDto commentRequestDto);

    void deleteById(long id);
}