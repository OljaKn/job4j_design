package ru.job4j.template;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@Disabled
class TamplateTest {
    private final Map<String, String> args = new HashMap<>();

    @BeforeEach
    void setUp() {
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
    }

    @Test
    void whenKeyIsNot() {
        assertThat(args.get("key")).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenKeyIsExcess() {
        args.put("key", "value");
        Assertions.assertThat(args.get("key")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTamplateIsTrue() {
        Template templ = new Template();
        String template = "I am a ${name}, Who are ${subject}? ";
        String rsl = templ.produce(template, args);
        assertThat(rsl).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

}