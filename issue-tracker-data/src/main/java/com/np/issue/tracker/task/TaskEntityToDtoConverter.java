package com.np.issue.tracker.task;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityToDtoConverter implements Converter<Task, TaskDto> {

    @NonNull
    @Override
    public TaskDto convert(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .build();
    }

}
