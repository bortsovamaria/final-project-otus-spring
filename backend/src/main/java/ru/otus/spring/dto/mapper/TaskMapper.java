package ru.otus.spring.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Task;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;

@Component
@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDto toDto(Task task);

    TaskFullResponseDto toFullDto(Task task);

    Task toDomain(TaskResponseDto taskResponseDto);

    Task toFullDomain(TaskFullResponseDto taskResponseDto);

}