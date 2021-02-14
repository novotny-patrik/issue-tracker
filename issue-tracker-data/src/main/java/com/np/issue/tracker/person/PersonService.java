package com.np.issue.tracker.person;

import java.util.Optional;

public interface PersonService {

    Optional<Person> findByLogin(String login);

}
