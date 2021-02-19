package com.np.issue.tracker.task;

import com.np.issue.tracker.person.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> findAll();

    List<TaskDto> findByPerson(Person person);

    Optional<TaskDto> findById(UUID id);

    TaskDto save(TaskDto taskDto);

    void deleteById(String id);
}
