package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Attachment;
import ru.otus.spring.dto.mapper.AttachmentMapperImpl;
import ru.otus.spring.dto.mapper.TaskMapper;
import ru.otus.spring.dto.response.AttachmentFullResponseDto;
import ru.otus.spring.repository.AttachmentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AttachmentServiceImpl.class, AttachmentMapperImpl.class})
class AttachmentServiceImplTest {

    @MockBean
    private AttachmentRepository attachmentRepository;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskService taskService;

    @Autowired
    private AttachmentService attachmentService;

    @Test
    void findById() {
        AttachmentFullResponseDto expectedResponse = AttachmentFullResponseDto.builder()
                .id(1L)
                .build();
        when(attachmentRepository.findById(eq(1L))).thenReturn(
                Optional.ofNullable(Attachment.builder().id(1L).build())
        );
        AttachmentFullResponseDto actualResponse = attachmentService.findById(1L);

        assertEquals(expectedResponse, actualResponse);
    }
}