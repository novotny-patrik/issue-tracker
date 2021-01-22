package com.np.issue.tracker.task;

import com.np.issue.tracker.mapper.EntityDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class TaskServiceImpl extends EntityDtoMapper<Task, TaskDto> implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        return Optional.of(taskDto)
                .map(this::toEntity)
                .map(taskRepository::save)
                .map(this::toDto)
                .orElseThrow();
    }

    @Override
    public Class<Task> getEntityClass() {
        return Task.class;
    }

    @Override
    public Class<TaskDto> getDtoClass() {
        return TaskDto.class;
    }
}
