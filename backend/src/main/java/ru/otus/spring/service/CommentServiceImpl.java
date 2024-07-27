package ru.otus.spring.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Task;
import ru.otus.spring.dto.mapper.CommentMapper;
import ru.otus.spring.dto.mapper.TaskMapper;
import ru.otus.spring.dto.request.CommentCreateRequestDto;
import ru.otus.spring.dto.request.CommentUpdateRequestDto;
import ru.otus.spring.dto.response.CommentResponseDto;
import ru.otus.spring.dto.response.TaskFullResponseDto;
import ru.otus.spring.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final CommentMapper commentMapper;

    @Override
    public CommentResponseDto findById(long id) {
        return commentRepository.findById(id)
                .map(commentMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));
    }

    @Override
    public List<CommentResponseDto> findByTaskId(long taskId) {
        TaskFullResponseDto task = taskService.findById(taskId);
        return task.getComments();
    }

    @Transactional
    @Override
    public CommentResponseDto insert(CommentCreateRequestDto commentCreateRequestDto) {
        Task task = taskMapper.toFullDomain(taskService.findById(commentCreateRequestDto.getTaskId()));
        Comment comment = Comment.builder()
                .text(commentCreateRequestDto.getText())
                .task(task)
                .build();
        return commentMapper.toDTO(commentRepository.save(comment));
    }

    @Transactional
    @Override
    public CommentResponseDto update(CommentUpdateRequestDto commentUpdateRequestDto) {
        Optional<Comment> commentOpt = commentRepository.findById(commentUpdateRequestDto.getId());
        if (commentOpt.isPresent()) {
            Comment comment = commentOpt.get();
            comment.setText(commentUpdateRequestDto.getText());
            return commentMapper.toDTO(commentRepository.save(comment));
        }
        throw new EntityNotFoundException("Comment with id = " + commentUpdateRequestDto.getId() + " not found");
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        }
        throw new EntityNotFoundException("Comment with id = " + id + " not found");
    }
}