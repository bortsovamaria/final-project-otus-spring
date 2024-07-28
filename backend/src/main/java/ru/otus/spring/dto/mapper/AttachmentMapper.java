package ru.otus.spring.dto.mapper;

import org.mapstruct.Mapper;
import ru.otus.spring.domain.Attachment;
import ru.otus.spring.dto.response.AttachmentFullResponseDto;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentFullResponseDto toDTO(Attachment comment);
}