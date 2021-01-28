package com.np.issue.tracker.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskDtoToEntityConverter dtoToEntityConverter;
    private final TaskEntityToDtoConverter entityToDtoConverter;

    public TaskServiceImpl(
            TaskRepository taskRepository,
            TaskDtoToEntityConverter dtoToEntityConverter,
            TaskEntityToDtoConverter entityToDtoConverter) {
        this.taskRepository = taskRepository;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(entityToDtoConverter::convert)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<TaskDto> findById(UUID id) {
        return taskRepository.findById(id)
                .map(entityToDtoConverter::convert);
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        return Optional.of(taskDto)
                .map(dtoToEntityConverter::convert)
                .map(taskRepository::save)
                .map(entityToDtoConverter::convert)
                .orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        taskRepository.deleteById(UUID.fromString(id));
    }

}
