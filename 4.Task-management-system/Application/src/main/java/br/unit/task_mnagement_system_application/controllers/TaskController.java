
package br.unit.task_mnagement_system_application.controllers;

import br.unit.task_mnagement_system_application.models.TaskDTO;
import br.unit.task_mnagement_system_application.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


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
        validateNullId(task.getId());
        return taskService.createTask(task);
    }

    @GetMapping("/task/{id}")
    private TaskDTO findTaskById(@PathVariable(value = "id") Integer id) {
        validateId(id);
        return taskService.findTaskById(id);
    }

    @GetMapping("/task")
    private ArrayList<TaskDTO> findAllTasks() {
        return taskService.findAllTasks();
    }

    @PutMapping("/task/{taskId}")
    private boolean updateTaskById(@PathVariable(value = "taskId") Integer taskId, @Valid @ModelAttribute TaskDTO task) {
        validateId(taskId);
        validateNullId(task.getId());
        return taskService.updateTaskById(taskId, task);
    }

    @DeleteMapping("/task/{id}")
    private boolean deleteTaskById(@PathVariable(value = "id") Integer id) {
        validateId(id);
        return taskService.deleteTaskById(id);
    }


    // Other methods

    private void validateId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O Id não pode ser vazio");
        } else if (id < 1) {
            throw new IllegalArgumentException("O id não pode ser um valor negativo");
        }
    }

    private void validateNullId(Integer id) {
        if (id != null) {
            throw new IllegalArgumentException("O id da tarefa deve ser nulo.");
        }
    }
}
