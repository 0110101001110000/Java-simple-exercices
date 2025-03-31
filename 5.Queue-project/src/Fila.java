

// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
public class Fila<T> {


    // Attributes

    private Celula inicio;
    private Celula fim;
    private int    tamanho;
    private String tipo;


    // Constructors

    public Fila() {
        this("normal");
    }

    public Fila(String tipo) {
        setInicio(null);
        setFim(null);
        setTamanho(0);
        setTipo(tipo);
    }


    // Getter methods

    private Celula getInicio() {
        return inicio;
    }

    private Celula getFim() {
        return fim;
    }

    private int getTamanho() {
        return tamanho;
    }

    public String getTipo() {
        return tipo;
    }


    // Setter methods

    private void setInicio(Celula inicio) {
        this.inicio = inicio;
    }

    private void setFim(Celula fim) {
        this.fim = fim;
    }

    private void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    private void setTipo(String tipo) {
        if (!(tipo.equalsIgnoreCase("normal")) && !(tipo.equalsIgnoreCase("especial"))) {
            throw new IllegalArgumentException("O tipo da lista somente pode ser: 'normal' ou 'especial'");
        }
        this.tipo = tipo;
    }


    // Main methods

    public void inserir(T elemento) {
        Celula newCelula = new Celula(elemento);
        if (this.isEmpity()) {
            setInicio(newCelula);
        } else {
            getFim().setProximo(newCelula);
        }
        setFim(newCelula);
        setTamanho(getTamanho() + 1);
    }

    public boolean existeDado(T elemento) {
        if (this.isEmpity()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return (getInicio().getElemento().equals(elemento));
    }

    public boolean isEmpity() {
        return (getTamanho() == 0);
    }

    public T recuperar() {
        if (this.isEmpity()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return (T) getInicio().getElemento();
    }
}
