package pac;

import java.util.*;

public class DictionaryDriver<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private Node<K, V> root;

    /**
     * constructor for initializing dic.
     *
     * @param initialEntries->list of key value pair(dic.)
     */
    public DictionaryDriver(List<Map.Entry<K, V>> initialEntries) {
        for (var pair : initialEntries) {
            addWordToDictionary(pair.getKey(), pair.getValue());
        }
    }

    /**
     * function will add word to dictionary
     */
    @Override
    public void addWordToDictionary(K key, V value) {
        root = addWordToDictionaryHelper(root, key, value);
    }

    /**
     * helper function for adding node
     *
     * @param node->root of the tree/sub-tree
     * @param key->key of pair
     * @param value->value of pair
     * @return node->new created node
     */
    private Node<K, V> addWordToDictionaryHelper(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = addWordToDictionaryHelper(node.left, key, value); 
        }else if (cmp > 0) {
            node.right = addWordToDictionaryHelper(node.right, key, value); 
        }else {
            node.value = value;
        }
        return node;
    }

    /**
     * function will get value corressponding to the key
     */
    @Override
    public String getMeaningOfAWord(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left; 
            }else if (cmp > 0) {
                node = node.right; 
            }else {
                return node.value.toString();
            }
        }
        return "Word not found";
    }

    /**
     * will delete word from dictionary
     */
    @Override
    public String deleteWordFromDictionary(K key) {
        if (getMeaningOfAWord(key).equals("Word not found")) {
            return "Word not found";
        } else {
            root = deleteWordFromDictionaryHelper(root, key);
            return "Word deleted successfully";
        }
    }

    /**
     * *
     * delete key-value pair from dictionary
     *
     * @param node->root node of the tree/subtree
     * @param key->key which needs to be deleted
     * @return node->new created node
     */
    private Node<K, V> deleteWordFromDictionaryHelper(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = deleteWordFromDictionaryHelper(node.left, key); 
        }else if (cmp > 0) {
            node.right = deleteWordFromDictionaryHelper(node.right, key); 
        }else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node<K, V> temp = min(node.right);
            node.key = temp.key;
            node.value = temp.value;
            node.right = deleteWordFromDictionaryHelper(node.right, temp.key);
        }
        return node;
    }

    /**
     * will get min. node of the tree
     *
     * @param node->represents node of the sub-tree for which we need key-value
     * pair
     * @return min. node
     */
    private Node<K, V> min(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * will get sorted list of nodes
     */
    @Override
    public List<Map.Entry<K, V>> getSortedItems() {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    /**
     * will perform inorder traversal
     *
     * @param node->node of the sub-tree
     * @param result list of key-value pair
     */
    private void inOrderTraversal(Node<K, V> node, List<Map.Entry<K, V>> result) {
        if (node != null) {
            inOrderTraversal(node.left, result);
            result.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
            inOrderTraversal(node.right, result);
        }
    }

    /**
     * will get items in a range
     */
    @Override
    public List<Map.Entry<K, V>> getItemsInRange(K k1, K k2) {
        List<Map.Entry<K, V>> result = new ArrayList<>();
        inOrderRange(root, k1, k2, result);
        return result;
    }

    /**
     * will get items in a range
     *
     * @param node->node of the sub-tree
     * @param k1->lower bound key
     * @param k2->upper bound key
     * @param result->list of key value pair
     */
    private void inOrderRange(Node<K, V> node, K k1, K k2, List<Map.Entry<K, V>> result) {
        if (node == null) {
            return;
        }

        int cmpLow = k1.compareTo(node.key);
        int cmpHigh = k2.compareTo(node.key);

        if (cmpLow < 0) {
            inOrderRange(node.left, k1, k2, result);
        }
        if (cmpLow <= 0 && cmpHigh >= 0) {
            result.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
        }
        if (cmpHigh > 0) {
            inOrderRange(node.right, k1, k2, result);
        }
    }
}
