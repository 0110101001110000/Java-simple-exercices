
package br.unit.task_mnagement_system_application.controllers;

import br.unit.task_mnagement_system_application.models.TaskDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
@RestController("/api/task")
public class TaskController {


    // Attributes

    private final TaskService taskService;


    // Controllers

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    // Main methods

    @PostMapping
    private boolean createTask(@Valid @ModelAttribute TaskDTO task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    private TaskDTO findTaskById(@PathVariable(value = "id") int id) {
        return taskService.findTaskById(id);
    }

    @GetMapping
    private List<TaskDTO> findAllTasks() {
        return taskService.findAllTasks();
    }

    @PutMapping("/{id}")
    private boolean updateTaskById(@PathVariable(value = "id") int id, @Valid @ModelAttribute TaskDTO task) {
        return taskService.updateTaskById(id, task);
    }

    @DeleteMapping("/{id}")
    private boolean deleteTaskById(@PathVariable(value = "id") int id) {
        return taskService.deleteTaskById(id);
    }
}
