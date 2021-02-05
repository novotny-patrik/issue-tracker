package com.np.issue.tracker.person;

import com.np.issue.tracker.base.BaseEntity;
import com.np.issue.tracker.role.Role;
import com.np.issue.tracker.task.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {

    private String name;
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(
            mappedBy = "assignee",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    Set<Task> assignedTasks = new HashSet<>();

    public Person() {
    }
}
