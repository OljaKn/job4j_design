package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        final Node<T> newElem = new Node<T>(value, null);
        Node<T> last = head;
        if (head == null) {
            head = newElem;
        } else {
            while (last.next != null) {
                last = last.next;
        }
            last.next = newElem;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Node<T> rsl = head;
        Objects.checkIndex(index, size);
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T headElemIt = head.item;
        Node headElem = head.next;
        head.next = null;
        head.item = null;
        head = headElem;
        size--;
        modCount++;
        return headElemIt;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            Node<T> actualElem = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return actualElem != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T rsl = actualElem.item;
                actualElem = actualElem.next;
                return rsl;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}