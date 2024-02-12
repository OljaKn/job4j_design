package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
            cache.putIfAbsent(key, new SoftReference<V>(value));
    }

    public final V get(K key) {
        V value;
        if (!cache.containsKey(key)) {
            value = load(key);
            put(key, value);
        } else {
            SoftReference<V> val = cache.get(key);
            value = val.get();
            if (value == null) {
                value = load(key);
                put(key, value);
            }
        }
        return value;
    }

    protected abstract V load(K key);
}