package com.np.issue.tracker.security;

import com.np.issue.tracker.login.LoginService;
import com.np.issue.tracker.login.LoginUserDetails;
import com.np.issue.tracker.person.Person;
import com.np.issue.tracker.person.PersonService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityUserContext implements UserContext {

    private final LoginService loginService;
    private final PersonService personService;

    public SpringSecurityUserContext(
            LoginService loginService,
            PersonService personService) {
        this.loginService = loginService;
        this.personService = personService;
    }

    @Override
    public Optional<Person> getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        String login = getLogin(authentication.getPrincipal());
        Optional<Person> personOpt = personService.findByLogin(login);

        if (personOpt.isEmpty()) {
            throw new IllegalStateException("Could not find user with login " + login);
        }

        return personOpt;
    }

    @Override
    public void setCurrentUser(Person person) {

        if (person.getLogin() == null) {
            throw new IllegalArgumentException("login cannot be null");
        }

        UserDetails userDetails = loginService.loadUserByUsername(person.getLogin());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                person.getPassword(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getLogin(Object person) {

        if (person instanceof Person) {
            return ((Person) person).getLogin();
        }

        if (person instanceof LoginUserDetails) {
            return ((LoginUserDetails) person).getUsername();
        }

        return null;
    }
}
