
package br.unit;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @author 01101010-01110000
 * @since 1.0
 */
public class HashTable<T> {


    // Attributes

    private final ListaDuplamenteEncadeada<T>[] vector;
    private final int length;
    private int size;


    // Constructors

    /**
     * ...
     * @since 1.0
     */
    public HashTable() {
        this(701);
    }

    /**
     * ...
     * @param length
     * @since 1.0
     */
    public HashTable(int length) {
        this.setSize(0);
        this.length = this.validateLength(length);
        this.vector = new ListaDuplamenteEncadeada[getLength()];
    }


    // Getter methods

    private ListaDuplamenteEncadeada<T>[] getVector() {
        return vector;
    }

    private int getLength() {
        return length;
    }

    private int getSize() {
        return size;
    }


    // Setter methods

    private void setSize(int size) {
        this.size = size;
    }


    // Main methods

    private int transform(String string) {
        this.notNull(string);
        this.notBlank(string);

        int stringNumber = 0;
        for (char letter : string.toCharArray()) {
            stringNumber = (stringNumber * 128 + letter) % this.getLength();
        }

        return stringNumber;
    }

    private int hash(String string) {
        return (this.transform(string) % this.getLength());
    }


    // Validation methods

    private int validateLength(int length) {
        return length;
    }

    private void notNull(Object object) {
        if (object == null) { throw new NullPointerException("Não pode ser nulo"); }
    }

    private void notBlank(String string) {
        if (string.isBlank()) { throw new IllegalArgumentException("A string não pode ser vazia: '', ' '"); }
    }


    // Other methods

    // ...

}
