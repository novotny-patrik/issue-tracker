package com.np.issue.tracker.person;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoToEntityConverter implements Converter<PersonDto, Person> {

    @Override
    public Person convert(PersonDto personDto) {
        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .login(personDto.getLogin())
                .password(personDto.getPassword())
                .build();
    }
}
