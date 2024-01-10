package ru.job4j.serialization.json;

import java.io.Serializable;
import java.util.Arrays;

public class Employee {
    private final String name;
    private final int age;
    private final boolean probation;
    private final Card card;
    private final String[] function;

    public Employee(String name, int age, boolean probation, Card card, String[] function) {
        this.name = name;
        this.age = age;
        this.probation = probation;
        this.card = card;
        this.function = function;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\''
                + ", age=" + age
                + ", probation=" + probation
                + ", card=" + card
                + ", function=" + Arrays.toString(function) + '}';
    }
}
