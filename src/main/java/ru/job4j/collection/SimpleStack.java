package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linkedL = new ForwardLinked<T>();

    public T pop() {
        return linkedL.deleteFirst();
    }

    public void push(T value) {
        linkedL.addFirst(value);
    }
}