package com.np.issue.tracker.login;

import com.np.issue.tracker.person.Person;
import com.np.issue.tracker.person.PersonService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    private final PersonService personService;

    public LoginService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Person> personOpt = personService.findByLogin(login);

        if (personOpt.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }

        return LoginUserDetails.of(personOpt.get());
    }
}
