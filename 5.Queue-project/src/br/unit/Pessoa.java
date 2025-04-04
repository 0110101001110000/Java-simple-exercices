
package br.unit;


// Init -------------------------------------------------------------------- //


/**
 * Implementa uma pessoa com os atributos <i>nome</i>, <i>sexo</i> e <i>idade</i>.
 * @author FragaMateus
 * @since 1.0
 */
public class Pessoa {


    // Attributes

    private String nome;
    private String sexo;
    private String idade;


    // Constructors

    /**
     * Cria uma nova pessoa especificando <i>nome</i>, <i>sexo</i> e <i>idade</i>.
     * @param nome o nome da pessoa
     * @param sexo o sexo da pessoa
     * @param idade a idade da pessoa
     * @since 1.0
     */
    public Pessoa(String nome, String sexo, String idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }


    // Other methods

    @Override
    public String toString() {
        return nome + " (" + sexo + ", " + idade + " anos)";
    }
}
