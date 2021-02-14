package com.np.issue.tracker.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String HAS_ANY_ROLE_ANONYMOUS = "hasAnyRole('ANONYMOUS', 'USER')";
    private static final String HAS_ROLE_USER = "hasRole('USER')";
    private static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";

    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Qualifier("loginService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(
                authorizeRequests -> authorizeRequests
                        .antMatchers("/admin/h2/**").permitAll()
                        .antMatchers("/**").access(HAS_ROLE_USER)
        );

        // The default AccessDeniedException
        http.exceptionHandling(handler -> handler
                .accessDeniedPage("/errors/403")
        );

        // Login Configuration
        http.formLogin(form -> form
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .failureUrl("/login/form?error")
                .usernameParameter("username") // redundant
                .passwordParameter("password") // redundant
                .defaultSuccessUrl("/default", true)
                .permitAll()
        );

        // Allow anonymous users
        http.anonymous();

        // CSRF is enabled by default, with Java Config
        http.csrf().disable();

        // Cross Origin Resource Sharing
        http.cors().disable();

        // HTTP Security Headers
        http.headers().disable();

        // Enable <frameset> in order to use H2 web console
        http.headers().frameOptions().disable();
    }

    @Bean
    @Description("Configure Password Encoder")
    public PasswordEncoder passwordEncoder() {

        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder(4));

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    @Description("Configure Web Security")
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/css/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/img/**")
                .antMatchers("/webjars/**")
        ;

        // Thymeleaf needs to use the Thymeleaf configured FilterSecurityInterceptor
        // and not the default Filter from AutoConfiguration.
        final HttpSecurity http = getHttp();
        web.postBuildAction(() -> web.securityInterceptor(http.getSharedObject(FilterSecurityInterceptor.class)));

    }
}
