package ru.otus.spring.service;

import org.springframework.web.multipart.MultipartFile;
import ru.otus.spring.dto.response.AttachmentFullResponseDto;
import ru.otus.spring.dto.response.AttachmentResponseDto;

import java.util.List;

public interface AttachmentService {
    AttachmentFullResponseDto store(MultipartFile file, Integer taskId);

    AttachmentFullResponseDto findById(Long id);

    List<AttachmentResponseDto> findByTaskId(Long id);
}
