package com.np.issue.tracker.person;

import com.np.issue.tracker.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class Person extends BaseEntity {

    private String name;
    private String login;
    private String password;

}
