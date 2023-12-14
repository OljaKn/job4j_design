package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;
    private Node<E> last;

    @Override
    public void add(E value) {
        Node<E> newElem = new Node<>(value, null);
        Node<E> tail = last;
        if (tail == null || size == 0) {
            head = newElem;
        } else {
            last.next = newElem;
        }
        last = newElem;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> rsl = head;
        Objects.checkIndex(index, size);
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> actualElem = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return actualElem != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = actualElem.item;
                actualElem = actualElem.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}