package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Priority;
import ru.otus.spring.domain.Status;
import ru.otus.spring.domain.Task;
import ru.otus.spring.domain.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override
    @EntityGraph(attributePaths = {"createdBy", "updatedBy", "assignedTo", "status", "priority"})
    List<Task> findAll();

    List<Task> findByAssignedTo(User assignedTo);

    List<Task> findByCreatedBy(User createdBy);

    List<Task> findByUpdatedBy(User createdBy);

    List<Task> findTasksByPriority(Priority priority);

    List<Task> findTasksByStatus(Status id);
}