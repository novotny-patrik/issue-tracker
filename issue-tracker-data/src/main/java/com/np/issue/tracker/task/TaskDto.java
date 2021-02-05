package com.np.issue.tracker.task;

import com.np.issue.tracker.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TaskDto extends BaseDto {

    private String title;
    private String description;

    public TaskDto() {
    }
}