
package br.unit.seatwise_project.models;


// Init -------------------------------------------------------------------- //


public class Client {


    // Attributes

    private Long   id;
    private String email;
    private String password;


    // Constructors

    public Client(Long id, String email, String password) {
        this.id       = id;
        this.email    = email;
        this.password = password;
    }


    // Getter methods

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    // Setter methods

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // Main methods

    @Override
    public String toString() {
        return String.format("%d,%s,%s", getId(), getEmail(), getPassword());
    }
}
