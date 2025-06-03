
package br.unit.seatwise_project.model;


// Init -------------------------------------------------------------------- //


public class Client {


    // Attributes

    private Long   id;
    private String email;
    private String password;
    private String phone;


    // Constructors

    public Client(Long id, String email, String password, String phone) {
        this.id       = id;
        this.email    = email;
        this.password = password;
        this.phone    = phone;
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

    public String getPhone() {
        return phone;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // Main methods

    @Override
    public String toString() {
        return String.format("%s: %d,%s,%s,%s", super.toString(), this.getId(), this.getEmail(), this.getPassword(), this.getPhone());
    }
}
