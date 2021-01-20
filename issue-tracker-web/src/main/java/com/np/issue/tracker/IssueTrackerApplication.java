package com.np.issue.tracker;

import com.np.issue.tracker.task.TaskDto;
import com.np.issue.tracker.task.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class IssueTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(TaskService taskService) {
        return (args) -> List.of(
                TaskDto.builder()
                        .title("Read a book" )
                        .description("Read a book before sleep." )
                        .build(),
                TaskDto.builder()
                        .title("Clean workspace" )
                        .description("Throw away garbage and clean things up." )
                        .build(),
                TaskDto.builder()
                        .title("Foo" )
                        .description("Foo bar" )
                        .build()
        ).forEach(taskService::save);
    }

}
