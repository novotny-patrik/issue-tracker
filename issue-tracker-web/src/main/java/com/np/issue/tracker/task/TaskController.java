package com.np.issue.tracker.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {

        addMoreTestData();
        model.addAttribute("tasks", taskService.findAll());

        return "tasks";
    }

    private void addMoreTestData() {
        List.of(
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
