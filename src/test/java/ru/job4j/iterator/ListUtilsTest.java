package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> elements;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
        elements = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, i -> i < 5);
        assertThat(input).hasSize(0);
    }

    @Test
    void whenReplace() {
        ListUtils.replaceIf(input, i -> i <= 1, 2);
        assertThat(input).hasSize(2).containsSequence(2, 3);
    }

    @Test
    void whenRemoveAll() {

        ListUtils.removeAll(elements, input);
        assertThat(elements).hasSize(2).containsSequence(5, 6);
    }
}