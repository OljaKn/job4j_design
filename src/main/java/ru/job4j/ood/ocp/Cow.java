package ru.job4j.ood.ocp;

public class Cow extends Animal {
    public Cow(String name, int age) {
        super(name, age);
    }
    public void giveMilk() {
        System.out.println("Cow gives milk");
    }
}
