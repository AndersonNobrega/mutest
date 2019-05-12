package org.lar.FileUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MultiMap<K, V> extends LinkedHashMap<K, List<V>> {

    void map(K key, V value) {
        List<V> elementsForKey = get(key);
        if (elementsForKey == null) {
            elementsForKey = new ArrayList<>();
            super.put(key, elementsForKey);
        }
        elementsForKey.add(value);
    }
}