package br.unit.task_mnagement_system_application.db;

/**
 * Classe desenvolvida por Jackson
 * @author Jackson
 */

public class Celula {

    private Celula proximo;
    private Celula anterior;  // Adicionado para suportar a navegação bidirecional da lista duplamente encadeada
    private Object elemento;

    public Celula() {
        this.proximo = null;
        this.anterior = null; // Inicialização explícita do novo campo anterior
    }

    public Celula(Object elemento) {
        this.elemento = elemento;
        this.proximo = null;
        this.anterior = null; // Inicialização do novo campo anterior para evitar referências inválidas
    }

    public Celula(Celula proximo, Celula anterior, Object elemento) { //Adicionado parâmetro anterior no construtor
        this.proximo = proximo;
        this.anterior = anterior; // Novo parâmetro anterior
        this.elemento = elemento;
    }

    public Celula getProximo() {
        return proximo;
    }

    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }

    public Celula getAnterior() { // Adicionado get para anterior
        return anterior;
    }

    public void setAnterior(Celula anterior) { // Adicionado set para anterior
        this.anterior = anterior;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
}

//teste
