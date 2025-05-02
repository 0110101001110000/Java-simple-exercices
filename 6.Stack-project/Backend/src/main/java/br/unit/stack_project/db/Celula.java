package br.unit.stack_project.db;

/**
 * Representa uma célula em uma estrutura encadeada, contendo um elemento e um ponteiro para a próxima célula.
 * @author fragamateus
 * @since 1.0
 */
class Celula {
    private Object elemento;
    private Celula proximo;

    /**
     * Cria uma nova célula com o elemento especificado.
     * @param elemento o elemento a ser armazenado na célula
     * @since 1.0
     */
    public Celula(Object elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    /**
     * Retorna o elemento armazenado na célula.
     * @return o elemento da célula
     * @since 1.0
     */
    public Object getElemento() {
        return elemento;
    }

    /**
     * Retorna a próxima célula na estrutura encadeada.
     * @return a próxima célula, ou <i>null</i> se não houver próxima
     * @since 1.0
     */
    public Celula getProximo() {
        return proximo;
    }

    /**
     * Define a próxima célula na estrutura encadeada.
     * @param proximo a próxima célula a ser definida
     * @since 1.0
     */
    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }
}