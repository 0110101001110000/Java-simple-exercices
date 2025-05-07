
package br.unit;


// Init -------------------------------------------------------------------- //


/**
 * Implementa uma estrutura de dados de Tabela Hash, onde os elementos são adicionados e removidos de um vetor, sua posição (index) no vetor é definida atravéz do hash do nome do usuário.
 * Na abordagem, os usuários são armazenados em uma estrutura de dados Encadeada (ListaSimplesmenteEncadeada) para evitar colisões.
 * @author 01101010-01110000
 * @since 1.0
 */
public class HashTable {


    // Attributes

    private final ListaSimples<User>[] vector;
    private final int length;
    private int size;


    // Constructors

    /**
     * Tabela hash é criada e iniciada como vazia e com length padrão de 701
     * @since 1.0
     */
    public HashTable() {
        this(701);
    }

    /**
     * Tabela hash é criada e iniciada como vazia e com length personalizado
     * @param length tamanho do vetor da tabela hash
     * @since 1.0
     */
    public HashTable(int length) {
        this.onlyPositive(length);

        this.setSize(0);
        this.length = length;
        this.vector = new ListaSimples[getLength()];
    }


    // Getter methods

    private ListaSimples<User>[] getVector() {
        return this.vector;
    }

    public int getLength() {
        return this.length;
    }


    // Setter methods

    private void setSize(int size) {
        this.notNegative(size);
        if (size > this.getLength()) { throw new IllegalArgumentException("O 'size'(a) da tabela hash não pode ser maior que seu 'length'(P)"); }
        this.size = size;
    }


    // Main methods

    private int transform(User user) {
        String userName = user.getName();

        this.notNull(userName);
        this.notBlank(userName);

        int stringNumber = 0;
        for (char letter : userName.toCharArray()) {
            stringNumber = (stringNumber * 128 + letter) % this.getLength();
        }

        return stringNumber;
    }

    private int hash(User user) {
        return (this.transform(user) % this.getLength());
    }

    /**
     * Retorna true se a tabela hash está vazia; false caso contrário
     * @return true se a tabela está vazia, false caso contrário
     * @since 1.0
     */
    public boolean isEmpty() {
        return (this.getSize() == 0);
    }

    /**
     * Retorna true se a tabela hash está cheia; false caso contrário
     * @return true se a tabela hash está cheia, false caso contrário
     * @since 1.0
     */
    public boolean isFull() {
        return (this.getSize() == this.getLength());
    }

    /**
     * Retorna o fator de carga da tabela hash
     * @return o fator de carga da tabela hash
     * @since 1.0
     */
    public float getLoadFactor() {
        return ((float) this.getSize() / this.getLength());
    }

    /**
     * Insere o usuário na tabela, retornando a posição onde ele foi inserido
     * @param user o usuário que será inserido na tabela
     * @return a posição onde o usuário foi inserido
     * @since 1.0
     */
    public int insert(User user) {
        int index = this.hash(user);

        if (this.isFull()) { throw new RuntimeException("A tabela hash está cheia"); }

        if (this.getVector()[index] == null) {
            this.getVector()[index] = new ListaSimples<>(user);
            this.setSize(this.getSize() + 1);
        } else {
            this.getVector()[index].adicionaFim(user);
        }

        return index;
    }

    /**
     * Retorna a posição onde o usuário se encontra ou -1 caso não o encontre
     * @param user o usuário que será procurado na tabela
     * @return a posição onde o usuário se encontra ou -1 caso não o encontre
     * @since 1.0
     */
    public int search(User user) {
        int index = this.hash(user);

        if (this.getVector()[index] != null) {
            int userPosition = this.getVector()[index].recuperaIndexDado(user);
            if (userPosition != -1) {
                if (this.getVector()[index].recupera(userPosition).isEnabled()) {
                    return index;
                }
            }
        }

        return -1;
    }

    /**
     * O usuário é removido, retornando true se ele estava na tabela e false caso contrário
     * @param user o usuário que será removido da tabela
     * @return true se o usuário estava na tabela e false caso contrário
     * @since 1.0
     */
    public boolean delete(User user) {
        int index = this.hash(user);

        if (this.getVector()[index] != null) {
            int userPosition = this.getVector()[index].recuperaIndexDado(user);
            if (userPosition != -1) {
                this.getVector()[index].recupera(userPosition).setEnabled(false);
                return true;
            }
        }

        return false;
    }

    /**
     * Todos os itens da tabela são descartados e ela torna-se uma tabela hash vazia
     * @since 1.0
     */
    public void clear() {
        for (int i = 0; i < this.getVector().length; i++) {
            if (this.getVector()[i] != null) {
                this.getVector()[i] = null;
                this.setSize(this.getSize() - 1);
            }
        }
    }

    /**
     * Retorna o número de elementos em uso na tabela hash
     * @return o número de elementos em uso na tabela hash
     * @since 1.0
     */
    public int getSize() {
        return size;
    }


    // Validation methods

    private void onlyPositive(float number) {
        if (number < 1) { throw new IllegalArgumentException("O valor deve ser positivo: maior que zero"); }
    }

    private void notNegative(float number) {
        if (number < 0) { throw new IllegalArgumentException("O valor não pode ser negativo: menor que zero"); }
    }

    private void notNull(Object object) {
        if (object == null) { throw new NullPointerException("Não pode ser nulo"); }
    }

    private void notBlank(String string) {
        if (string.isBlank()) { throw new IllegalArgumentException("A string não pode ser vazia: '', ' '"); }
    }
}
