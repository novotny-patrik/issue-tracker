package com.np.issue.tracker.person;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityToDtoConverter implements Converter<Person, PersonDto> {

    @Override
    public PersonDto convert(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getPersonName())
                .login(person.getLogin())
                .password(person.getPassword())
                .build();
    }
}
