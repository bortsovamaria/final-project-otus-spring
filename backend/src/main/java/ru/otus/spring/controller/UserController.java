package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.UserService;

import java.util.List;

@Tag(name = "Пользователи")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получить список пользователей")
    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getAll();
    }
}
