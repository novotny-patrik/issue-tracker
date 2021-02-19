package com.np.issue.tracker.security;

import com.np.issue.tracker.person.Person;

import java.util.Optional;

public interface UserContext {

    Optional<Person> getCurrentUser();

    void setCurrentUser(Person person);

}
