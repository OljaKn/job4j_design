package ru.job4j.ood.isp;

public interface Animal {
    void eat();
    void crawl();
    void fly();
}
class Cat implements Animal {

    @Override
    public void eat() {

    }

    @Override
    public void crawl() {
    }

    @Override
    public void fly() {
        throw new IllegalArgumentException("Cats can't fly");
    }
}

class Bird implements Animal {

    @Override
    public void eat() {

    }

    @Override
    public void crawl() {
        throw new IllegalArgumentException("Birds can't crawl");
    }

    @Override
    public void fly() {

    }
}
