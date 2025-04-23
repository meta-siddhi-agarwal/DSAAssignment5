package pac;

import java.util.List;
import java.util.Map;

public interface Dictionary<K extends Comparable<K>, V> {

    void addWordToDictionary(K key, V value);

    String getMeaningOfAWord(K key);

    String deleteWordFromDictionary(K key);

    List<Map.Entry<K, V>> getSortedItems();

    List<Map.Entry<K, V>> getItemsInRange(K k1, K k2);
}
