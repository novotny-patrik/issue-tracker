package com.np.issue.tracker.task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> findAll();

    Optional<TaskDto> findById(UUID id);

    TaskDto save(TaskDto taskDto);
}
