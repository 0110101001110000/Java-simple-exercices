
package br.unit.task_mnagement_system_application.services;

import br.unit.task_mnagement_system_application.models.TaskDTO;
import br.unit.task_mnagement_system_application.repositories.TaskDAO;

import java.util.ArrayList;


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

    public boolean createTask(TaskDTO task) {
        return taskDao.createTask(task);
    }

    public TaskDTO findTaskById(Integer id) {
        return taskDao.findTaskById(id);
    }

    public ArrayList<TaskDTO> findAllTasks() {
        return taskDao.findAllTasks();
    }

    public boolean updateTaskById(Integer id, TaskDTO task) {
        return taskDao.updateTaskById(id, task);
    }

    public boolean deleteTaskById(Integer id) {
        return taskDao.deleteTaskById(id);
    }
}
