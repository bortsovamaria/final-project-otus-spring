package ru.otus.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + attachment.getName())
                .body(attachment);
    }

    @Operation(summary = "Получить список файлов по ID задачи")
    @GetMapping("/api/attachments/task")
    public ResponseEntity<List<AttachmentResponseDto>> getCommentsByTaskId(@RequestParam long id) {
        return new ResponseEntity<>(attachmentService.findByTaskId(id), HttpStatus.OK);
    }

    @Operation(summary = "Загрузить файл в задачу")
    @PostMapping("/api/attachments/upload")
    public ResponseEntity<AttachmentResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                                         @RequestParam Integer taskId) {
        attachmentService.store(file, taskId);
        return ResponseEntity.status(HttpStatus.OK).body(new AttachmentResponse(file.getOriginalFilename(),
                "Uploaded the file successfully: " + file.getOriginalFilename()));
    }

    @Operation(summary = "Удалить файл по ID")
    @DeleteMapping("/api/attachments/{id}")
    public ResponseEntity<Void> deleteAttachmentById(@PathVariable long id) {
        attachmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
