package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn = 0;
    private int sizeOut = 0;


    public T poll() {
        if (sizeIn == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeOut == 0) {
            while (sizeIn != 0) {
                out.push(in.pop());
                sizeOut++;
                sizeIn--;
            }
        }
        T temp = null;
        if (sizeOut != 0) {
            temp = out.pop();
            sizeOut--;
        }
        return temp;
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}