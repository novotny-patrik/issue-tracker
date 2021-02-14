package com.np.issue.tracker.person;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findByLogin(String login) {
        return personRepository.findByLogin(login);
    }
}
