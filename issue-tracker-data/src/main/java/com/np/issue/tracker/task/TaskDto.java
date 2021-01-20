package com.np.issue.tracker.task;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class TaskDto {

    UUID id;
    String title;
    String description;

    Task toEntity() {
        return Task.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }

}
