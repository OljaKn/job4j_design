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
        boolean rsl = false;
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        int hashCode = key == null ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
            if (table[index] == null) {
                table[index] = new MapEntry<>(key, value);
                count++;
                modCount++;
                rsl = true;
            }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity *= 2;
        for (MapEntry<K, V> entry: table) {
            if (entry != null) {
                int hashCode = entry.key == null ? 0 : entry.key.hashCode();
                int index = indexFor(hash(hashCode));
                newTable[index] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int hashCode = key == null ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null) {
            if (Objects.equals(table[index].key, key)) {
                rsl = table[index].value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int hashCode = key == null ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null) {
                if (Objects.equals(table[index].key, key)) {
                    table[index] = null;
                    count--;
                    modCount++;
                    rsl = true;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NonCollisionMap<K, V> that = (NonCollisionMap<K, V>) o;
        return capacity == that.capacity && count == that.count && modCount == that.modCount && Arrays.equals(table, that.table);
    }

    public static void main(String[] args) {
        NonCollisionMap map = new NonCollisionMap();
        System.out.println(map.hash(65536));
    }
}