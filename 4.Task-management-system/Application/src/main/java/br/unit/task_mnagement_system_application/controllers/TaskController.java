
package br.unit.task_mnagement_system_application.controllers;

import br.unit.task_mnagement_system_application.models.IdDTO;
import br.unit.task_mnagement_system_application.models.TaskDTO;
import br.unit.task_mnagement_system_application.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
@RestController
public class TaskController {


    // Attributes

    private final TaskService taskService = new TaskService();


    // Main methods

    @PostMapping("/task")
    private boolean createTask(@Valid @ModelAttribute TaskDTO task) {
        return taskService.createTask(task);
    }

    @GetMapping("/task/{id}")
    private TaskDTO findTaskById(@Valid @PathVariable(value = "id") IdDTO id) {
        return taskService.findTaskById(id);
    }

    @GetMapping("/task")
    private List<TaskDTO> findAllTasks() {
        return taskService.findAllTasks();
    }

    @PutMapping("/task/{id}")
    private boolean updateTaskById(@Valid @PathVariable(value = "id") IdDTO id, @Valid @ModelAttribute TaskDTO task) {
        return taskService.updateTaskById(id, task);
    }

    @DeleteMapping("/task/{id}")
    private boolean deleteTaskById(@Valid @PathVariable(value = "id") IdDTO id) {
        return taskService.deleteTaskById(id);
    }
}
