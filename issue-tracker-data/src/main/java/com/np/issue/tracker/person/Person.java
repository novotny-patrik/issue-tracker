package com.np.issue.tracker.person;

import com.np.issue.tracker.base.BaseEntity;
import com.np.issue.tracker.role.Role;
import com.np.issue.tracker.task.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
public class Person extends BaseEntity {

    private String name;
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", roles=" + Arrays.toString(roles.stream().map(Role::getName).toArray()) +
                ", assignedTasks=" + Arrays.toString(assignedTasks.stream().map(Task::getId).toArray()) +
                '}';
    }
}
