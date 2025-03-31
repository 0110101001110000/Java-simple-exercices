

// Init -------------------------------------------------------------------- //


/**
 * Implementa uma estrutura de dados de fila FIFO (First In, First Out), onde os elementos são adicionados e removidos do final.
 * A fila segue a regra de que o primeiro elemento adicionado será o último a ser removido.
 * @author 01101010-01110000
 */
public class Fila<T> {


    // Attributes

    /**
     * Primeiro elemento armazenado na Fila.
     */
    private Celula inicio;

    /**
     * Ultimo elemento armazenado na Fila.
     */
    private Celula fim;

    /**
     * Quantidade de elementos armazenados na Fila.
     */
    private int    tamanho;

    /**
     * Tipo da Fila.
     * O tipo da Fila, pode ser <i>normal</i> ou <i>especial</i>
     */
    private String tipo;


    // Constructors

    /**
     * Cria uma nova Fila com seus atributos padrões.
     * @since 1.0
     */
    public Fila() {
        this("normal");
    }

    /**
     * Cria uma nova Fila com seus atributos padrões exceto o seu atributo <i>tipo</i>.
     * @param tipo o tipo da Fila, pode ser <i>normal</i> ou <i>especial</i>
     * @since 1.0
     */
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

    /**
     * Adiciona um elemento no fim da Fila.
     * @param elemento o elemento à ser adicionado
     * @since 1.0
     */
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

    /**
     * Verifica se o elemento fornecido é igual ao primeiro elemento da Fila.
     * @param elemento o elemento à ser comparado com o primeiro da Fila
     * @return <i>true</i> caso o elemento fornecido seja igual ao ao primeiro elemento da Fila, <i>false</i> caso contrário
     * @since 1.0
     */
    public boolean existeDado(T elemento) {
        if (this.isEmpity()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return (getInicio().getElemento().equals(elemento));
    }

    /**
     * Verifica se a Fila está vazia.
     * @return <i>false</i> caso o tamanho da lista seja superior a 0, <i>true</i> caso contrário
     * @since 1.0
     */
    public boolean isEmpity() {
        return (getTamanho() == 0);
    }

    /**
     * Retorna o objeto do primeiro item da Fila. Obs: não o remove.
     * @return o objeto do primeiro item da Fila
     * @since 1.0
     */
    public T recuperar() {
        if (this.isEmpity()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return (T) getInicio().getElemento();
    }
}
