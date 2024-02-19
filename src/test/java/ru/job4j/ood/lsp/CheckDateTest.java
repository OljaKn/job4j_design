package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class CheckDateTest {

    @Test
    void whenTodayIsCreateDayThen33() {
        CheckDate cd = new CheckDate();
        LocalDate create = LocalDate.now().minusDays(5);
        LocalDate expiry = LocalDate.now().plusDays(10);
        assertThat(cd.percent(create, expiry)).isEqualTo(33);
    }

    @Test
    void whenTodayIsCreateDayThen100() {
        CheckDate cd = new CheckDate();
        LocalDate create = LocalDate.now().minusDays(14);
        LocalDate expiry = LocalDate.now();
        assertThat(cd.percent(create, expiry)).isEqualTo(100);
    }
}