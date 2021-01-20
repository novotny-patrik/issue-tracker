package com.np.issue.tracker.task;

import java.util.List;

public interface TaskService {

    List<TaskDto> findAll();

    TaskDto save(TaskDto taskDto);
}
