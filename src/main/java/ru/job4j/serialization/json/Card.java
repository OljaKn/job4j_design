package ru.job4j.serialization.json;

public class Card {
    private final int numberCard;

    public Card(int numberCard) {
        this.numberCard = numberCard;
    }

    @Override
    public String toString() {
        return "Card{" + ", numberCard=" + numberCard + '}';
    }
}
