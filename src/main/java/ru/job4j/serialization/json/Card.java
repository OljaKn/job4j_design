package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card")
public class Card {
    @XmlAttribute
    private int numberCard;
    public Card() {
    }

    public Card(int numberCard) {
        this.numberCard = numberCard;
    }

    public int getNumberCard() {
        return numberCard;
    }

    @Override
    public String toString() {
        return "Card{" + ", numberCard=" + numberCard + '}';
    }
}
