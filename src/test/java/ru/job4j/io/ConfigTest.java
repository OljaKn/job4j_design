package ru.job4j.io;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
        assertThrows(IllegalArgumentException.class, () -> {
            config.load();
        });
    }

    @Test
    void whenPairNotKey() {
        String path = "data/pair_not_key.properties";
        Config config = new Config(path);
        Map<String, String> values = new HashMap<String, String>();
        assertThrows(IllegalArgumentException.class, () -> {
            config.load();
        });
        assertThat(values.size()).isZero();
    }

}