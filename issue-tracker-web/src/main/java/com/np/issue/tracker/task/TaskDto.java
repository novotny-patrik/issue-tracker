package com.np.issue.tracker.task;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TaskDto {

    String title;
    String description;

}
