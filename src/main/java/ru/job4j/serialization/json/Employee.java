package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
@XmlRootElement(name = "employee")
public class Employee {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean probation;
    private Card card;
    @XmlElementWrapper
    @XmlElement(name = "function")
    private String[] function;

    public Employee() {
    }

    public Employee(String name, int age, boolean probation, Card card, String[] function) {
        this.name = name;
        this.age = age;
        this.probation = probation;
        this.card = card;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getProbation() {
        return probation;
    }

    public Card getCard() {
        return card;
    }

    public String[] getFunction() {
        return function;
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
