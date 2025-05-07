
package br.unit;


// Init -------------------------------------------------------------------- //


/**
 * Implementa um usuário simples com nome, senha e status (Ativo/Inativo).
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
     * Usuário é criado com nome e senha personalizados
     * @param name nome do usuário
     * @param password senha do usuário
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
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
