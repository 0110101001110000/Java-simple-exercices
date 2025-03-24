
package br.unit.task_mnagement_system_application.services;

import br.unit.task_mnagement_system_application.models.IdDTO;
import br.unit.task_mnagement_system_application.models.TaskDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
public class TaskService {


    // Attributes

    private final TaskDAO taskDao;


    // Constructors

    public TaskService() {
        this.taskDao = new TaskDAO();
    }


    // Main methods

    @PostMapping
    public boolean createTask(@Valid @ModelAttribute TaskDTO task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("O id deve ser nulo.");
        }
        return taskDao.createTask(task);
    }

    @GetMapping("/{id}")
    public TaskDTO findTaskById(@Valid @PathVariable(value = "id") IdDTO id) {
        return taskDao.findTaskById(id);
    }

    @GetMapping
    public List<TaskDTO> findAllTasks() {
        return taskDao.findAllTasks();
    }

    @PutMapping("/{id}")
    public boolean updateTaskById(@Valid @PathVariable(value = "id") IdDTO id, @Valid @ModelAttribute TaskDTO task) {
        return taskDao.updateTaskById(id, task);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTaskById(@Valid @PathVariable(value = "id") IdDTO id) {
        return taskDao.deleteTaskById(id);
    }
}
