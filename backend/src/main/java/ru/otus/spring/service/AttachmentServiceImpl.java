package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.spring.domain.Attachment;
import ru.otus.spring.domain.Task;
import ru.otus.spring.dto.mapper.AttachmentMapper;
import ru.otus.spring.dto.mapper.TaskMapper;
import ru.otus.spring.dto.response.AttachmentFullResponseDto;
import ru.otus.spring.dto.response.AttachmentResponseDto;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.exceptions.FailParseFileException;
import ru.otus.spring.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    private final TaskMapper taskMapper;

    private final TaskService taskService;

    private final AttachmentMapper attachmentMapper;

    @Override
    public AttachmentFullResponseDto store(MultipartFile file, Integer taskId) {
        Task task = taskMapper.toDomain(taskService.findById(taskId));
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Attachment attachment;
        try {
            attachment = Attachment.builder()
                    .name(fileName)
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .task(task)
                    .build();
        } catch (IOException e) {
            throw new FailParseFileException("An error occurred while reading the file = " + file.getOriginalFilename());
        }
        return attachmentMapper.toDTO(attachmentRepository.save(attachment));
    }

    @Override
    public AttachmentFullResponseDto findById(Long id) {
        return attachmentMapper.toDTO(attachmentRepository.findById(id).get());
    }

    @Override
    public List<AttachmentResponseDto> findByTaskId(Long taskId) {
        TaskFullResponseDto task = taskService.findFullById(taskId);
        return task.getAttachments();
    }

    @Override
    public void deleteById(long id) {
        if (taskService.existById(id)) {
            attachmentRepository.deleteById(id);
        }
        throw new EntityNotFoundException("Task with id " + id + " not found");
    }
}
