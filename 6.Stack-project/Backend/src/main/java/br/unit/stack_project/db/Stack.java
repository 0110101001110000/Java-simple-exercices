package br.unit.stack_project.db;


// Init -------------------------------------------------------------------- //


/**
 * Implementa uma estrutura de dados de pilha dinâmica LIFO (Last In, First Out), onde os elementos são adicionados e removidos do topo.
 * A pilha segue a regra de que o último elemento adicionado será o primeiro a ser removido.
 * @author fragamateus
 * @since 1.0
 */
public class Stack<T> {


    // Attributes

    private Celula topo;    // Topo da pilha
    private int tamanho;    // Contador de elementos


    // Constructors

    /**
     * Cria uma nova Pilha com seus atributos padrões.
     * @since 1.0
     */
    public Stack() {
        this.topo = null;
        this.tamanho = 0;
    }

    /**
     * Cria uma nova Pilha inicializando-a com um elemento no topo.
     * @param elemento o elemento inicial a ser adicionado à pilha
     * @since 1.0
     */
    public Stack(T elemento) {
        this.topo = null;
        this.tamanho = 0;
        this.push(elemento);
    }


    // Main methods

    /**
     * Adiciona um elemento ao topo da pilha.
     * @param elemento o elemento a ser adicionado
     * @since 1.0
     */
    public void push(T elemento) {
        Celula nova = new Celula(elemento);
        if (this.tamanho == 0) {
            topo = nova;
        } else {
            nova.setProximo(topo);
            topo = nova;
        }
        this.tamanho += 1;
    }

    /**
     * Remove e retorna o elemento do topo da pilha.
     * @return o elemento removido do topo da pilha, ou <i>null</i> se a pilha estiver vazia
     * @since 1.0
     */
    public T pop() {
        if (this.tamanho == 0) {
            System.out.println("A pilha está vazia!");
            return null;
        } else {
            T elemento = (T) topo.getElemento();
            topo = topo.getProximo();
            this.tamanho -= 1;
            return elemento;
        }
    }

    /**
     * Retorna o elemento do topo da pilha sem removê-lo.
     * @return o elemento no topo da pilha, ou <i>null</i> se a pilha estiver vazia
     * @since 1.0
     */
    public T peek() {
        if (this.tamanho == 0) {
            System.out.println("A pilha está vazia!");
            return null;
        } else {
            return (T) topo.getElemento();
        }
    }

    /**
     * Retorna o tamanho atual da pilha.
     * @return o número de elementos na pilha
     * @since 1.0
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Verifica se a pilha está vazia.
     *
     * @return true se a pilha estiver vazia, false caso contrário.
     */
    public boolean isEmpty() {
        return topo == null;
    }
}