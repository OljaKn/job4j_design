package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("four")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first", "second");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three");
        assertThat(set)
                .contains("second")
                .doesNotContain("four")
                .anySatisfy(e -> {
                    assertThat(e).isEqualTo("three");
                });

    }

    @Test
    void checkMap() {
        Map<String, Integer> map = Map.of("key", 1, "two", 2, "Map", 3);
        assertThat(map).hasSize(3)
                .containsKeys("key")
                .doesNotContainKey("null")
                .containsEntry("Map", 3);
    }
}