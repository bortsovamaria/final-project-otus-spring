package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.spring.dto.response.AttachmentFullResponseDto;
import ru.otus.spring.dto.response.AttachmentResponse;
import ru.otus.spring.dto.response.AttachmentResponseDto;
import ru.otus.spring.service.AttachmentService;

import java.util.List;

@Tag(name = "Файлы")
@RestController
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Operation(summary = "Получить файл по ID")
    @GetMapping("/api/attachments/{id}")
    public ResponseEntity<AttachmentFullResponseDto> getFile(@PathVariable Long id) {
        AttachmentFullResponseDto attachment = attachmentService.findById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(attachment);
    }

    @Operation(summary = "Загрузить файл в задачу")
    @PostMapping("/api/attachments/upload")
    public ResponseEntity<AttachmentResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                                         @RequestParam Integer taskId) {
        attachmentService.store(file, taskId);
        return ResponseEntity.status(HttpStatus.OK).body(new AttachmentResponse(file.getOriginalFilename(),
                "Uploaded the file successfully: " + file.getOriginalFilename()));

    }

    @Operation(summary = "Получить список комментариев по ID задачи")
    @GetMapping("/api/attachments/task")
    public ResponseEntity<List<AttachmentResponseDto>> getCommentsByTaskId(@RequestParam long id) {
        return new ResponseEntity<>(attachmentService.findByTaskId(id), HttpStatus.OK);
    }
}
