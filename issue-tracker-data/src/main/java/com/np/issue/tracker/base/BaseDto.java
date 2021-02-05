package com.np.issue.tracker.base;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
public class BaseDto {

    private UUID id;

    public BaseDto() {
    }

}
