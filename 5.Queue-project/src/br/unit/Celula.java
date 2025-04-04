
package br.unit;


// Init -------------------------------------------------------------------- //


/**
 * Representa uma célula de uma fila.
 * Cada célula contém um elemento e um ponteiro para a próxima célula.
 * @author 01101010-01110000
 * @since 1.0
 */
public class Celula {


    // Attributes

    private Celula proximo;
    private Object elemento;


    // Constructors

    /**
     * Cria uma nova célula com ponteiro nulo e sem elemento.
     * @since 1.0
     */
    public Celula() {
        this(null, null);
    }

    /**
     * Cria uma nova célula com o dado elemento e ponteiro nulo.
     * @param elemento o elemento a ser armazenado
     * @since 1.0
     */
    public Celula(Object elemento) {
        this(null, elemento);
    }

    /**
     * Cria uma nova célula com o dado elemento e ponteiro para a próxima célula.
     * @param proximo   o próximo elemento da lista
     * @param elemento  o elemento a ser armazenado
     * @since 1.0
     */
    public Celula(Celula proximo, Object elemento) {
        setProximo(proximo);
        setElemento(elemento);
    }


    // Getter methods

    public Celula getProximo() {
        return proximo;
    }

    public Object getElemento() {
        return elemento;
    }


    // Setter methods

    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}
