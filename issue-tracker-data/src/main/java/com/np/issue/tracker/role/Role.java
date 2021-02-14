package com.np.issue.tracker.role;

import com.np.issue.tracker.base.BaseEntity;
import com.np.issue.tracker.person.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
public class Role extends BaseEntity implements Serializable {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<Person> persons;

    public Role() {
    }
}
