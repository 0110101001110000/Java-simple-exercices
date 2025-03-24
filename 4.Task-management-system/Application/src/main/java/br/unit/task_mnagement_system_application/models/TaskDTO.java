
package br.unit.task_mnagement_system_application.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
public class TaskDTO {


    // Attributes

    @NotNull(message = "O id não pode ser nulo")
    @Positive(message = "O id deve ser um valor positivo")
    private int id;

    @NotNull(message = "O nome da tarefa não pode ser nulo")
    @Size(min = 2, max = 255, message = "O nome da tarefa deve ter entre 2 e 255 caracteres")
    private String taskName;


    // Constructors

    public TaskDTO(int id, String taskName) {
        setId(id);
        setTaskName(taskName);
    }


    // Getter methods

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }


    // Setter methods

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
