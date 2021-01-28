package com.np.issue.tracker.person;

import com.np.issue.tracker.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PersonDto extends BaseDto {

    private String name;
    private String login;
    private String password;

}
