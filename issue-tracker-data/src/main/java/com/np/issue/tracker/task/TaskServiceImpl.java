package com.np.issue.tracker.task;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(Task::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        return taskRepository.save(taskDto.toEntity()).toDto();

    }
}
