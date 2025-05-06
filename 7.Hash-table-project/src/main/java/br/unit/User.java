
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
    private boolean isEnabled;


    // Constructors

    /**
     * ...
     * @param name
     * @param password
     * @since 1.0
     */
    public User(String name, String password) {
        this.setName(name);
        this.setPassword(password);
        this.setEnabled(true);
    }


    // Getter methods

    public String getName() {
        return name;
    }

    private String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    // Setter methods

    private void setName(String name) {
        this.name = name;
    }

    private void setPassword(String password) {
        this.password = this.encryptPassword(password);
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    // Main methods

    private String encryptPassword(String password) {
        return password; // TO-DO
    }


    // Other methods

    // ...

}
