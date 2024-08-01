package ru.otus.spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;

    private String fullName;

    private String username;

    private String email;
}
