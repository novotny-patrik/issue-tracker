package com.np.issue.tracker.login;

import com.np.issue.tracker.person.Person;
import com.np.issue.tracker.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoginUserDetails implements UserDetails {

    private final Person person;

    public static LoginUserDetails of(Person person) {
        return new LoginUserDetails(person);
    }

    public LoginUserDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roleNames = person.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        String[] roleNamesArray = new String[roleNames.size()];
        roleNames.toArray(roleNamesArray);
        return AuthorityUtils.createAuthorityList(roleNamesArray);
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
