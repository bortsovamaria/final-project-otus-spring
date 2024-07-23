package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
