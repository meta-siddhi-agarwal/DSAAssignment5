package pac;

public class Node<K, V> {

    K key;
    V value;
    Node<K, V> left, right;

    /**
     * constructor for initializing nodes
     *
     * @param k->key of key-value pair
     * @param v->value of key-value pair
     */
    Node(K k, V v) {
        key = k;
        value = v;
        left = null;
        right = null;
    }
}
