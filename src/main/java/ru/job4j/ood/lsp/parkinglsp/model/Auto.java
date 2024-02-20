package ru.job4j.ood.lsp.parkinglsp.model;

import java.util.Objects;

public abstract class Auto {
    int size;
    String make;

    public Auto(int size, String make) {
        this.size = size;
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return size == auto.size && Objects.equals(make, auto.make);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, make);
    }
}
