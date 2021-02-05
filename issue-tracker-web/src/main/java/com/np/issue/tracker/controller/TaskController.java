package com.np.issue.tracker.controller;

import com.np.issue.tracker.task.TaskDto;
import com.np.issue.tracker.task.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String task(Model model, @Nullable @PathVariable(required = false) String id) {

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

    // Thymeleaf link can not perform DELETE method, for this case use GET
    @RequestMapping( value = "/task/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable String id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    @GetMapping("/tasks/my")
    public String getMyTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

}
