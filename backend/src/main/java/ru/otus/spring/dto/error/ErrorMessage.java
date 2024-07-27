package ru.otus.spring.dto.error;

import java.time.LocalDateTime;


public record ErrorMessage(int statusCode, LocalDateTime timestamp, String message, String description) {
}
