package ru.otus.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.spring.domain.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAll();

    User getCurrentUser();

    User findById(long id);

    User findByUsername(String username);
}