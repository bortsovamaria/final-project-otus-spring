package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Task;
import ru.otus.spring.dto.mapper.CommentMapperImpl;
import ru.otus.spring.dto.mapper.TaskMapper;
import ru.otus.spring.dto.response.CommentResponseDto;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {CommentServiceImpl.class, CommentMapperImpl.class})
class CommentServiceImplTest {
    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;


    @Test
    void findById() {
        CommentResponseDto expectedResponse = new CommentResponseDto(1L, "text");
        when(commentRepository.findById(eq(1L))).thenReturn(
                Optional.of(new Comment(1L, "text", Task.builder().build())));

        CommentResponseDto actualResponse = commentService.findById(1L);

        assertEquals(expectedResponse, actualResponse);
    }
}