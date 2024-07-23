package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
