
package br.unit.task_mnagement_system_application.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
public class IdDTO {


    // Attributes

    @NotNull(message = "O id não pode ser nulo")
    @Positive(message = "O id deve ser um valor positivo")
    private int id;


    // Constructors

    public IdDTO(int id, String taskName) {
        setId(id);
    }


    // Getter methods

    public int getId() {
        return id;
    }


    // Setter methods

    public void setId(int id) {
        this.id = id;
    }
}
