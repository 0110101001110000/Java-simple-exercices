

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
            adicionaFim(elemento);

        } else if ((posicao > 0) && (posicao < this.tamanho)) {

            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            while (iterador.hasNext()) {
                if ((posicao - 1) == index) {
                    Celula atual = iterador.getAtual();
                    Celula nova = new Celula(atual.getProximo(), elemento);
                    atual.setProximo(nova);
                    this.tamanho += 1;
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

    /**
     * @author 01101010-01110000
     */
    public void adicionaFim(T elemento) {
        Celula nova = new Celula(null, elemento);
        if (this.tamanho == 0) {
            adicionaInicio(elemento);
        } else {
            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            while (iterador.hasNext()) {
                Celula atual = iterador.getAtual();
                if (atual.getProximo() == null) {
                    atual.setProximo(nova);
                    this.tamanho += 1;
                    this.fim = nova;
                    break;
                }
                iterador.next();
                index++;
            }
        }
    }

    /**
     * @author 01101010-01110000
     */
    public boolean existeDado(T elemento) {

        if (this.tamanho == 0) {

            System.out.println("A lista está vazia!!");
            return false;

        } else {

            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            while (iterador.hasNext()) {
                Celula atual = iterador.getAtual();
                if (atual.getElemento().equals(elemento)) {
                    return true;
                }
                iterador.next();
                index++;
            }

        }

        return false;
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

    /**
     * @author 01101010-01110000
     */
    public void remove(int posicao) {

        if (posicao == 0) {
            removeInicio();

        } else if (posicao == (this.tamanho - 1)) {
            removeFim();

        } else if ((posicao > 0) && (posicao < (this.tamanho - 1))) {

            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            while (iterador.hasNext()) {
                if ((posicao - 1) == index) {
                    Celula atual = iterador.getAtual();
                    Celula prox = atual.getProximo();
                    atual.setProximo(prox.getProximo());
                    this.tamanho -= 1;
                    break;
                }
                iterador.next();
                index ++;
            }

        } else {
            System.out.println("A Posição " + posicao + " é Inválida!");
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

    /**
     * @author 01101010-01110000
     */
    public void removeFim() {

        if (this.tamanho == 0) {
            System.out.println("A lista está vazia!!");

        } else if (inicio == fim) {
            removeInicio();

        } else {

            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            Celula atual = null;
            Celula prox = null;
            while (iterador.hasNext()) {
                atual = iterador.getAtual();
                prox = atual.getProximo();
                if (this.fim.equals(prox)) {
                    atual.setProximo(null);
                    this.fim = atual;
                    this.tamanho -= 1;
                    break;
                }
                iterador.next();
                index ++;
            }
        }
    }


    // Other methods

    /**
     * Classe temporária, somente para testes
     * @author 01101010-01110000
     */
    public void mostrarItems() {

        if (this.tamanho == 0) {
            System.out.println("A lista está vazia!!");

        } else {

            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            while (iterador.hasNext()) {
                Celula atual = iterador.getAtual();
                Aluno a = (Aluno) atual.getElemento();
                System.out.println(a.getNome() + " - " + a.getIdade());
                iterador.next();
                index++;
            }
        }
    }
}
