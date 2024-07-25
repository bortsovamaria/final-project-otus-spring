package ru.otus.spring.service;

import ru.otus.spring.dto.request.CommentCreateRequestDto;
import ru.otus.spring.dto.request.CommentUpdateRequestDto;
import ru.otus.spring.dto.response.CommentResponseDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    CommentResponseDto findById(long id);

    List<CommentResponseDto> findByTaskId(long taskId);

    CommentResponseDto insert(CommentCreateRequestDto commentCreateRequestDto);

    CommentResponseDto update(CommentUpdateRequestDto commentUpdateRequestDto);

    void deleteById(long id);
}