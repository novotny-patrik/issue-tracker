package com.np.issue.tracker.registration;

import com.np.issue.tracker.person.Person;
import com.np.issue.tracker.person.PersonService;
import com.np.issue.tracker.role.RoleService;
import com.np.issue.tracker.security.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("registration")
@Slf4j
public class RegistrationController {

    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserContext userContext;

    public RegistrationController(
            PersonService personService,
            PasswordEncoder passwordEncoder,
            RoleService roleService,
            UserContext userContext) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userContext = userContext;
    }

    @GetMapping("/form")
    public String registration(final @ModelAttribute RegistrationDto registrationDto) {
        return "register";
    }

    @PostMapping("/new")
    public String registration(
            final @Valid RegistrationDto registrationDto,
            final BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> log.info("error: {}", e));
            return "register";
        }

        String login = registrationDto.getLogin();
        Optional<Person> personOpt = personService.findByLogin(login);
        if (personOpt.isPresent()) {
            result.rejectValue(
                    "login",
                    "errors.registration.login",
                    "Login address is already in use."
            );
            return "register";
        }

        Person person = Person.builder()
                .personName(registrationDto.getPersonName())
                .login(registrationDto.getLogin())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .roles(roleService.getRolesForNewPerson())
                .build();

        Person savedPerson = personService.save(person);
        userContext.setCurrentUser(savedPerson);

        return "redirect:/default";

    }

}
