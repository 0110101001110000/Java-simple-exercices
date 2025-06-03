
package br.unit.seatwise_project.model;


// Init -------------------------------------------------------------------- //


public class Chair {


    // Attributes

    private Long id;


    // Constructors

    public Chair(Long id) {
        this.id = id;
    }


    // Getter methods

    public Long getId() {
        return id;
    }


    // Setter methods

    public void setId(Long id) {
        this.id = id;
    }


    // Main methods

    @Override
    public String toString() {
        return String.format("%s: %d", super.toString(), this.getId());
    }
}
