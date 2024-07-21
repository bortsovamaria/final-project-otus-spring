package ru.otus.spring.dto.mapper;

import org.mapstruct.Mapper;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.dto.request.CommentRequestDto;
import ru.otus.spring.dto.response.CommentResponseDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentResponseDto toDTO(Comment comment);

    Comment toDomain(CommentRequestDto comment);
}