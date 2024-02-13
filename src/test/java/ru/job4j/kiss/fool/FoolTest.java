package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static java.lang.System.in;
import static org.assertj.core.api.Assertions.assertThat;


class FoolTest {
    @Test
    void whenPr3IsFalse() {
       Fool fool = new Fool();
       assertThat(fool.validate(15)).isEqualTo("FizzBuzz");
    }

    @Test
    void whenPr5IsTrue() {
        Fool fool = new Fool();
        assertThat(fool.validate(6)).isEqualTo("Fizz");
    }
}