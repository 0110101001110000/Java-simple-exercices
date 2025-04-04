package br.unit.task_mnagement_system_application.db;

/**
 * Classe desenvolvida por Jackson
 * @author Jackson
 */
public class ListaDuplamenteEncadeada<T> {  // Renomeado de ListaSimples

    private Celula inicio, fim;
    private int tamanho;

    public ListaDuplamenteEncadeada() {  // Renomeado de ListaSimples()
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }
    
    public ListaDuplamenteEncadeada(T elemento) {  // Renomeado de ListaSimples(T)
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
        this.adicionarInicio(elemento);  // Ajustado de adicionaInicio
    }

    public void adicionar(T elemento, int posicao) {  // Renomeado de adiciona
        if (posicao == 0) {
            adicionarInicio(elemento);
        } else if (posicao == this.tamanho) {
            adicionarFim(elemento);
        } else if (posicao > 0 && posicao < this.tamanho) {
            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            while (iterador.hasNext()) {
                if ((posicao - 1) == index) {
                    Celula atual = iterador.getAtual();
                    Celula nova = new Celula(atual.getProximo(), atual, elemento); //Adicionado atual como anterior do novo nó
                    atual.getProximo().setAnterior(nova); //Ajusta o ponteiro anterior do próximo nó para apontar para o novo nó
                    atual.setProximo(nova);
                    this.tamanho += 1;
                    break;
                }
                iterador.next();
                index++;
            }
        } else {
            System.out.println("A Posição " + posicao + " é Inválida!");
        }
    }

    public void adicionarInicio(T elemento) {  // Renomeado de adicionaInicio
        Celula nova = new Celula(elemento);
        if (this.tamanho == 0) {
            inicio = fim = nova;
        } else {
            nova.setProximo(inicio);
            inicio.setAnterior(nova); //Conecta o antigo início ao novo nó como anterior
            inicio = nova;
        }
        this.tamanho += 1;
    }

    public void adicionarFim(T elemento) {  // Renomeado de adicionaFim
        Celula nova = new Celula(null, fim, elemento); //Novo nó já aponta para o fim como anterior
        if (this.tamanho == 0) {
            adicionarInicio(elemento);  // Ajustado de adicionaInicio
        } else {
            fim.setProximo(nova); //Conecta o antigo fim ao novo nó (substitui o uso do iterador)
            fim = nova;
            this.tamanho += 1;
        }
    }

    public boolean existeDado(T elemento) {
        if (this.tamanho == 0) {
            System.out.println("A lista está vazia!!");
            return false;
        }
        Iterador iterador = new Iterador(this.inicio);
        while (iterador.hasNext()) {
            Celula atual = iterador.getAtual();
            if (atual.getElemento().equals(elemento)) {
                return true;
            }
            iterador.next();
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
                if (i == posicao) {
                    break;
                }
                it.next();
                i++;
            }
            return (T) it.getAtual().getElemento();
        }
    }

    public void remover(int posicao) {  // Renomeado de remove
        if (posicao == 0) {
            removerInicio();  // Ajustado de removeInicio
        } else if (posicao == (this.tamanho - 1)) {
            removerFim();  // Ajustado de removeFim
        } else if (posicao > 0 && posicao < (this.tamanho - 1)) {
            Iterador iterador = new Iterador(this.inicio);
            int index = 0;
            while (iterador.hasNext()) {
                if (posicao == index) { //Condição ajustada para remover o nó na posição exata
                    Celula atual = iterador.getAtual();
                    atual.getAnterior().setProximo(atual.getProximo()); //Religa o nó anterior ao próximo
                    atual.getProximo().setAnterior(atual.getAnterior()); //Religa o nó próximo ao anterior
                    this.tamanho -= 1;
                    break;
                }
                iterador.next();
                index++;
            }
        } else {
            System.out.println("A Posição " + posicao + " é Inválida!");
        }
    }

    public void removerInicio() {  // Renomeado de removeInicio
        if (this.tamanho == 0) {
            System.out.println("A lista está vazia!");
        } else if (inicio == fim) {
            inicio = fim = null;
            this.tamanho -= 1;
        } else {
            inicio = inicio.getProximo();
            inicio.setAnterior(null); //Remove a referência anterior do novo início
            this.tamanho -= 1;
        }
    }

    public void removerFim() {  // Renomeado de removeFim
        if (this.tamanho == 0) {
            System.out.println("A lista está vazia!!");
        } else if (inicio == fim) {
            removerInicio();  // Ajustado de removeInicio
        } else {
            fim = fim.getAnterior(); //Usa o ponteiro anterior diretamente (substitui o iterador)
            fim.setProximo(null); //Remove a referência ao próximo do novo fim
            this.tamanho -= 1;
        }
    }

    public void limpa() {  
        if (this.tamanho == 0) {
            System.out.println("A lista está vazia!");
        } else {
            inicio = fim = null;
            this.tamanho = 0;
        }
    }

    public int obterTamanho() {  // Renomeado de tamanho
        return this.tamanho;
    }

}