package br.unit.stack_project.db;

/**
 * Iterador para percorrer uma estrutura encadeada de células.
 * @author fragamateus
 * @since 1.0
 */
class Iterador {
    private Celula atual;

    /**
     * Cria um novo iterador a partir de uma célula inicial.
     * @param inicio a célula inicial para começar a iteração
     * @since 1.0
     */
    public Iterador(Celula inicio) {
        this.atual = inicio;
    }

    /**
     * Verifica se há mais elementos para iterar.
     * @return <i>true</i> se houver mais elementos, <i>false</i> caso contrário
     * @since 1.0
     */
    public boolean hasNext() {
        return atual != null;
    }

    /**
     * Avança para a próxima célula na estrutura encadeada.
     * @since 1.0
     */
    public void next() {
        atual = atual.getProximo();
    }

    /**
     * Retorna a célula atual do iterador.
     * @return a célula atual
     * @since 1.0
     */
    public Celula getAtual() {
        return atual;
    }
}