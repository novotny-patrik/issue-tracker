package com.np.issue.tracker.task;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoToEntityConverter implements Converter<TaskDto, Task> {

    @Override
    public Task convert(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .build();
    }
}
