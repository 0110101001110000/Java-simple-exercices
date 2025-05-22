
package br.unit.models;


// Init -------------------------------------------------------------------- //


public class HashTableEntity<Key, Value> {


    // Attributes

    private Key     key;
    private Value   value;
    private boolean enabled;


    // Constructors

    public HashTableEntity(Key key, Value value) {
        setKey(key);
        setValue(value);
        setEnabled(true);
    }


    // Getter methods

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public boolean isEnabled() {
        return enabled;
    }


    // Setter methods

    public void setKey(Key key) {
        this.key = key;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
