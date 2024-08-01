package ru.otus.spring.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.domain.User;

import java.util.List;
import java.util.Set;

@UtilityClass
public class UserUtils {

    public static List<User> getExpectedUsers() {
        return List.of(
                new User(1L, "Evgen", "evgen", "pass", "evgenmail", Set.of()),
                new User(1L, "Nikita", "nikit", "pass", "nikitamail", Set.of()),
                new User(1L, "Elena", "lena", "pass", "lenamail", Set.of())
        );
    }

    public static User getExpectedUser() {
        return User.builder()
                .id(1L)
                .fullName("Leontiy")
                .username("Leontiy")
                .password("pass")
                .email("email")
                .roles(Set.of())
                .build();
    }
}
