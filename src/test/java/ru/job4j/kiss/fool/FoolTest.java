package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class FoolTest {
    @Test
    void whenPr3IsFalse() {
       Fool fool = new Fool();
       boolean rsl = fool.pr3.test(4);
       assertThat(rsl).isFalse();
    }

    @Test
    void whenPr5IsTrue() {
        Fool fool = new Fool();
        boolean rsl = fool.pr5.test(30);
        assertThat(rsl).isTrue();
    }
}