package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        int index = checkIndex(key);
        boolean rsl = table[index] == null;
            if (rsl) {
                table[index] = new MapEntry<>(key, value);
                count++;
                modCount++;
            }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int checkIndex(K key) {
        int hashCode = Objects.hashCode(key);
        return indexFor(hash(hashCode));
    }

    private boolean checkEquals(K key) {
        int index = checkIndex(key);
        return Objects.equals(table[index].key, key);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry: table) {
            if (entry != null) {
                int index = checkIndex(entry.key);
                newTable[index] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = checkIndex(key);
        if (table[index] != null) {
            if (checkEquals(key)) {
                rsl = table[index].value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int index = checkIndex(key);
        boolean rsl = table[index] != null;
        if (rsl) {
                if (checkEquals(key)) {
                    table[index] = null;
                    count--;
                    modCount++;
                }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int currentIndex = 0;
            private int expendedModCount = modCount;

               @Override
               public boolean hasNext() {
                   if (expendedModCount != modCount) {
                       throw new ConcurrentModificationException();
                   }
                   while (currentIndex < capacity && table[currentIndex] == null) {
                       currentIndex++;
                   }
                   return currentIndex < capacity;
               }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[currentIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap map = new NonCollisionMap();
        System.out.println(map.hash(65536));
    }
}