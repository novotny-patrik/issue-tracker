package com.np.issue.tracker.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
public class H2Config {

    /**
     * Access the H2 Console:
     * http://localhost:8080/admin/h2/
     *
     * @return {@link ServletRegistrationBean} for the H2 admin Servlet
     */
    @Bean
    @Description("H2 Database admin Servlet")
    public ServletRegistrationBean<WebServlet> h2servletRegistration() {
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<>(new WebServlet());
        registrationBean.addUrlMappings("/admin/h2/*");
        return registrationBean;
    }
}
