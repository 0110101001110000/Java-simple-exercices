
package br.unit.task_mnagement_system_application.repositories;

import br.unit.task_mnagement_system_application.db.ListaDuplamenteEncadeada;
import br.unit.task_mnagement_system_application.models.TaskDTO;

import java.util.ArrayList;


// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
public class TaskDAO {


    // Attributes

    private final ListaDuplamenteEncadeada<TaskDTO> database;


    // Constructors

    public TaskDAO() {
        this.database = new ListaDuplamenteEncadeada<>();
    }


    // Main methods

    public boolean createTask(TaskDTO task) {
        task.setId(generateId());
        database.adicionarFim(task);
        return true;
    }

    public TaskDTO findTaskById(Integer id) {
        ArrayList<TaskDTO> elements = database.recuperarTodosElementos();
        for (TaskDTO task : elements) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public ArrayList<TaskDTO> findAllTasks() {
        return database.recuperarTodosElementos();
    }

    public boolean updateTaskById(Integer id, TaskDTO updatedTask) {
        if (database.obterTamanho() != 0) {
            ArrayList<TaskDTO> elements = database.recuperarTodosElementos();
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getId().equals(id)) {
                    updatedTask.setId(id);
                    database.adicionar(updatedTask, i + 1);
                    database.remover(i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteTaskById(Integer id) {
        if (database.obterTamanho() != 0) {
            ArrayList<TaskDTO> elements = database.recuperarTodosElementos();
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getId().equals(id)) {
                    database.remover(i);
                    return true;
                }
            }
        }
        return false;
    }


    // Other methods

    private Integer generateId() {
        if (database.obterTamanho() == 0) {
            return 1;
        }
        return database.ultimoElemento().getId() + 1;
    }
}
