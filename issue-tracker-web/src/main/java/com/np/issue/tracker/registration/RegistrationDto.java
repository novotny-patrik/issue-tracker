package com.np.issue.tracker.registration;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * RegistrationDto
 *
 * @since chapter03.00
 * @author mickknutson
 */
@Data
public class RegistrationDto {

    @NotEmpty(message="Name is required")
    private String personName;
    @NotEmpty(message="Login is required")
    private String login;
    @NotEmpty(message="Password is required")
    private String password;

}
