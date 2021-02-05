package com.np.issue.tracker.task;

import com.np.issue.tracker.base.BaseEntity;
import com.np.issue.tracker.person.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person assignee;

    public Task() {
    }

}
