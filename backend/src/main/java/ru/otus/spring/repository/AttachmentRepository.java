package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
