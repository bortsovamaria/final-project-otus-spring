package ru.otus.spring.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.User;
import ru.otus.spring.repository.UserRepository;
import ru.otus.spring.utils.UserUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private final List<User> expectedUsers = UserUtils.getExpectedUsers();

    private final User expectedUser = UserUtils.getExpectedUser();

    @Captor
    private ArgumentCaptor<User> argumentCaptor;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getAll();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void findById() {
        when(userRepository.findById(eq(1L))).thenReturn(Optional.ofNullable(expectedUser));

        User actualUser = userService.findById(1L);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByUsername() {
        when(userRepository.findByUsername(eq("Leontiy"))).thenReturn(expectedUser);

        User actualUser = userService.findByUsername("Leontiy");

        assertEquals(expectedUser, actualUser);
    }
}