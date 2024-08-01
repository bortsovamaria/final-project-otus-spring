package ru.otus.spring.dto.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.User;
import ru.otus.spring.dto.response.UserDto;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}