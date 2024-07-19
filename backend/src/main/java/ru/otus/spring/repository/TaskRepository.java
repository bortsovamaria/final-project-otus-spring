package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}