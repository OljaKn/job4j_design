package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkConteins() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(" key :value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(" key :value");
    }

    @Test
    void checkStartsWith() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=key:value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=key:value");
    }

    @Test
    void checkIndexOf() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("value="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value=");
    }
}