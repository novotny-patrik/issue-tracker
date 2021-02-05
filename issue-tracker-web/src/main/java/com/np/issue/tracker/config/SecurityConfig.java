package com.np.issue.tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(
                authorizeRequests -> authorizeRequests
                        .antMatchers("/**").permitAll()
                        .antMatchers("/admin/h2/**").permitAll()
        );
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
