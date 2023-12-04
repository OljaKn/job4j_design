package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(3, 0);
        String name = box.whatsThis();
        assertThat(name).contains("obj");
    }

    @Test
    void getNumberOfNotNull() {
        Box box = new Box(4, 8);
        int numder = box.getNumberOfVertices();
        assertThat(numder).isNotZero();
    }

    @Test
    void getNumberOfNegative() {
        Box box = new Box(-1, 3);
        int numder = box.getNumberOfVertices();
        assertThat(numder).isNegative();
    }

    @Test
    void checkBoolean() {
        Box box = new Box(8, 3);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void checkBooleanNotExist() {
        Box box = new Box(-1, 3);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void getAreaDoubleZero() {
        Box box = new Box(2, 0);
        double result = box.getArea();
        assertThat(result).isZero();
    }

    @Test
    void getAreaDouble() {
        Box box = new Box(4, 2);
        double result = box.getArea();
        assertThat(result).isGreaterThan(6.92d);
    }
}