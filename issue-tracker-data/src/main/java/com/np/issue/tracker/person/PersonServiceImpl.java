package com.np.issue.tracker.person;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findByLogin(String login) {
        return personRepository.findByLogin(login);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }
}
