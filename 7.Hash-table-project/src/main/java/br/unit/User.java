
package br.unit;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @author 01101010-01110000
 * @since 1.0
 */
public class User {


    // Attributes

    private String name;
    private String password;


    // Constructors

    /**
     * ...
     * @param name
     * @param password
     * @since 1.0
     */
    public User(String name, String password) {
        setName(name);
        setPassword(password);
    }


    // Getter methods

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    // Setter methods

    private void setName(String name) {
        this.name = name;
    }

    private void setPassword(String password) {
        this.password = this.encryptPassword(password);
    }


    // Main methods

    private String encryptPassword(String password) {
        return password; // TO-DO
    }


    // Other methods

    // ...

}
