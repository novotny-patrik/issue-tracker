package com.np.issue.tracker.task;

import com.np.issue.tracker.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
class Task extends BaseEntity {

    private String title;
    private String description;

    public Task() {
    }

}
