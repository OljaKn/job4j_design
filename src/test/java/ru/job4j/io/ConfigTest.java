package ru.job4j.io;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.beans.Beans.isInstanceOf;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr");
    }

    @Test
    void whenPairWithComment() {
        String path = "data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void whenPairNotKey() {
        String path = "data/pair_not_key.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, () -> {
            config.load();
        });
    }

    @Test
    void whenPairNotValue() {
        String path = "data/pair_not_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, () -> {
            config.load();
        });
    }

    @Test
    void whenPairWithoutSymbol() {
        String path = "data/pair_without_symbol.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, () -> {
            config.load();
        });
    }
}