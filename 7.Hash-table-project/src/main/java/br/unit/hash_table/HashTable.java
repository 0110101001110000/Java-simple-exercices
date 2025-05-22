
package br.unit.hash_table;

import br.unit.models.HashTableEntity;
import br.unit.linked_structure.ListaSimples;

import java.util.ArrayList;


// Init -------------------------------------------------------------------- //


/**
 * Implementa uma estrutura de dados de Tabela Hash, onde os elementos são adicionados e removidos de um vetor, sua posição (index) no vetor é definida através do hash da key.
 * Na abordagem, a HashTableEntity é armazenada numa estrutura de dados Encadeada (ListaSimplesmenteEncadeada) para evitar colisões.
 * @author 01101010-01110000
 * @since 1.0
 */
public class HashTable<Key, Value> {


    // Attributes

    private final ListaSimples<HashTableEntity<Key, Value>>[] vector;
    private final int length;
    private       int size;


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

    private ListaSimples<HashTableEntity<Key, Value>>[] getVector() {
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

    private int hash(Key key) {
        return (Math.abs(key.hashCode()) % this.getLength());
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
     * Insere o elemento na tabela, retornando a posição onde ele foi inserido
     * @param entity o elemento que será inserido na tabela
     * @return a posição onde o elemento foi inserido
     * @throws RuntimeException caso a tabela esteja completamente preenchida
     * @since 1.0
     */
    public int insert(HashTableEntity<Key, Value> entity) {
        int index = this.hash(entity.getKey());

        if (this.isFull()) { throw new RuntimeException("A tabela hash está cheia"); }

        if (this.getVector()[index] != null) {
            this.getVector()[index].adicionaFim(entity);
        } else {
            this.getVector()[index] = new ListaSimples<>(entity);
            this.setSize(this.getSize() + 1);
        }

        return index;
    }

    /**
     * Retorna a posição onde o elemento se encontra ou -1 caso não o encontre
     * @param key a chave primária do elemento que será procurado na tabela
     * @return a posição onde o elemento se encontra ou -1 caso não o encontre
     * @since 1.0
     */
    public int search(Key key) {
        int index = this.hash(key);

        if (this.getVector()[index] != null) {
            ArrayList<HashTableEntity<Key, Value>> users = this.getVector()[index].recuperaTodos();
            for (HashTableEntity<Key, Value> user : users) {
                if (user.getKey().equals(key) && user.isEnabled()) {
                    return index;
                }
            }
        }

        return -1;
    }

    /**
     * Retorna o elemento armazenado na estrutura encadeada caso o encontre ou null caso contrário
     * @param key a chave primária do elemento que será procurado na tabela
     * @return o elemento caso o encontre ou null caso contrário
     * @since 1.0
     */
    public HashTableEntity<Key, Value> getElement(Key key) {
        int index = this.hash(key);

        if (this.getVector()[index] != null) {
            ArrayList<HashTableEntity<Key, Value>> users = this.getVector()[index].recuperaTodos();
            for (HashTableEntity<Key, Value> user : users) {
                if (user.getKey().equals(key) && user.isEnabled()) {
                    return user;
                }
            }
        }

        return null;
    }

    /**
     * O elemento é removido, retornando true se ele estava na tabela e false caso contrário
     * @param key a chave primária do elemento que será removido da tabela
     * @return true se o elemento estava na tabela e false caso contrário
     * @since 1.0
     */
    public boolean delete(Key key) {
        int index = this.hash(key);

        if (this.getVector()[index] != null) {
            ArrayList<HashTableEntity<Key, Value>> users = this.getVector()[index].recuperaTodos();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getKey().equals(key) && (this.getVector()[index].recupera(i).isEnabled())) {
                    this.getVector()[index].recupera(i).setEnabled(false);
                    return true;
                }
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
