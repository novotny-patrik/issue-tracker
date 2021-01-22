package com.np.issue.tracker.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@Slf4j
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping({"/task", "/task/{id}"})
    public String task(Model model, @PathVariable(required = false) String id) {

        TaskDto taskDto = new TaskDto();

        if (id != null) {
            UUID uuid = null;

            try {
                uuid = UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                log.error("UUID.fromString({})", id, e);
            }

            if (uuid != null) {
                Optional<TaskDto> taskDtoOpt = taskService.findById(uuid);
                if (taskDtoOpt.isPresent()) {
                    taskDto = taskDtoOpt.get();
                }
            }
        }
        model.addAttribute("task", taskDto);
        return "task-form";
    }

    @PostMapping("/task")
    public String saveOrUpdate(@ModelAttribute TaskDto taskDto) {
        TaskDto savedTask = taskService.save(taskDto);
        log.debug("Saved task {}", savedTask);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    private void addMoreTestData() {
        List.of(
                TaskDto.builder()
                        .title("Read a book")
                        .description("Read a book before sleep.")
                        .build(),
                TaskDto.builder()
                        .title("Clean workspace")
                        .description("Throw away garbage and clean things up.")
                        .build(),
                TaskDto.builder()
                        .title("Foo")
                        .description("Foo bar")
                        .build()
        ).forEach(taskService::save);
    }

}
