

// Init -------------------------------------------------------------------- //


/**
 * Classe desenvolvida por Jackson
 * @author Jackson
 */
public class ListaSimples<T> {


    // Attributes

    private Celula inicio, fim;
    private int tamanho;


    // Constructors

    public ListaSimples() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }
    
    public ListaSimples(T elemento){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
        this.adicionaInicio(elemento);
    }


    // Main methods

    /**
     * @author 01101010-01110000
     */
    public void adiciona(T elemento, int posicao) {
        if (posicao == 0) {
            adicionaInicio(elemento);

        } else if (posicao == this.tamanho) {

            // adicionaFim(elemento);
            System.out.println("Adiciona elemento ao fim!!!");

        } else if ((posicao > 0) && (posicao < this.tamanho)) {

            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            while (iterador.hasNext()) {
                if ((posicao - 1) == index) {
                    Celula atual = iterador.getAtual();
                    Celula nova = new Celula(atual.getProximo(), elemento);
                    atual.setProximo(nova);
                    this.tamanho ++;
                    break;
                }
                iterador.next();
                index ++;

            }

        } else {
            System.out.println("A Posição " + posicao + " é Inválida!");
        }
    }

    public void adicionaInicio(T elemento) {
        Celula nova = new Celula(elemento);
        if (this.tamanho == 0) {
            inicio = fim = nova;
            this.tamanho += 1;
        } else {
            nova.setProximo(inicio);
            inicio = nova;
            this.tamanho += 1;
        }
    }

    public T Recupera(int posicao) {
        
        if (this.tamanho == 0) {
            
            System.out.println("A lista está vazia!!");
            return null;
            
        } else if (posicao < 0 || posicao >= this.tamanho) {
            
            System.out.println("A Posição " + posicao + " é Inválida!");
            return null;
            
        } else {
            
            Iterador it = new Iterador(this.inicio);
            int i = 0;
            while (it.hasNext()) {
                if (i != posicao) {
                    it.next();
                    i++;
                } else {
                    break;
                }
            }
            
            return (T) it.getAtual().getElemento();
        }
    }

    public void removeInicio() {
        
        if (this.tamanho == 0) {
            System.out.println("A lista está vazia!");
            
        } else if (inicio == fim) {
            
            inicio = fim = null;
            this.tamanho -= 1;
            
        } else {
            
            inicio = inicio.getProximo();
            this.tamanho -= 1;
        }
    }
}
