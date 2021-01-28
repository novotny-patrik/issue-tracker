package com.np.issue.tracker.person;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonEntityToDtoConverter entityToDtoConverter;
    private final PersonDtoToEntityConverter dtoToEntityConverter;

    public PersonServiceImpl(
            PersonRepository personRepository,
            PersonEntityToDtoConverter entityToDtoConverter,
            PersonDtoToEntityConverter dtoToEntityConverter) {
        this.personRepository = personRepository;
        this.entityToDtoConverter = entityToDtoConverter;
        this.dtoToEntityConverter = dtoToEntityConverter;
    }
}
