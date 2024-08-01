package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Task;
import ru.otus.spring.dto.mapper.TaskMapper;
import ru.otus.spring.dto.mapper.TaskMapperImpl;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.dto.response.TaskResponseDto;
import ru.otus.spring.repository.PriorityRepository;
import ru.otus.spring.repository.StatusRepository;
import ru.otus.spring.repository.TaskRepository;
import ru.otus.spring.utils.TaskUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {TaskServiceImpl.class, TaskMapperImpl.class})
class TaskServiceImplTest {
    private static final Task expectedTask = TaskUtils.getTask();

    public static final List<Task> expectedTaskList = TaskUtils.getTaskList();

    @MockBean
    private UserService userService;

    @MockBean
    private StatusRepository statusRepository;

    @MockBean
    private PriorityRepository priorityRepository;

    @Autowired
    private TaskMapper taskMapper;

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    void findFullById() {
        var expectedTaskDto = TaskFullResponseDto.builder()
                .id(1L)
                .title("title")
                .description("descrp")
                .build();

        when(taskRepository.findById(eq(1L))).thenReturn(Optional.of(expectedTask));

        TaskFullResponseDto actualTaskDto = taskService.findFullById(1L);

        assertEquals(expectedTaskDto, actualTaskDto);
    }

    @Test
    void findById() {
        var expectedTaskDto = TaskResponseDto.builder()
                .id(1L)
                .title("title")
                .description("descrp")
                .build();

        when(taskRepository.findById(eq(1L))).thenReturn(Optional.ofNullable(expectedTask));

        TaskResponseDto actualTaskDto = taskService.findById(1L);

        assertEquals(expectedTaskDto, actualTaskDto);
    }

    @Test
    void findAll() {
        List<TaskResponseDto> expectedTaskDtoList = expectedTaskList.stream().map(taskMapper::toDto).toList();
        when(taskRepository.findAll()).thenReturn(expectedTaskList);

        List<TaskResponseDto> actualTaskList = taskService.findAll();

        assertEquals(expectedTaskDtoList, actualTaskList);
    }
}