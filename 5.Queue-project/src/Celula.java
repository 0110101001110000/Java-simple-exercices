

// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
public class Celula {


    // Attributes

    private Celula proximo;
    private Object elemento;


    // Constructors

    public Celula() {
        this(null, null);
    }

    public Celula(Object elemento) {
        this(null, elemento);
    }

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
