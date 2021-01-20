package com.np.issue.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class IssueTrackerApplication {

    private static final Logger log = LoggerFactory.getLogger(IssueTrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return (args) -> log.info("Hello world!");
    }

}
