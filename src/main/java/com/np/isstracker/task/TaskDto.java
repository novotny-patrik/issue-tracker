package com.np.isstracker.task;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TaskDto {

    String title;
    String description;

}
