package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linkedL = new ForwardLinked<T>();
    private int count;

    public T pop() {
        count++;
        return linkedL.deleteFirst();
    }

    public void push(T value) {
        count++;
        linkedL.addFirst(value);
    }
}