package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ControlQualityTest {
    @Test
    void whenAddThenGoesToShopTrue() throws ParseException {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Shop());
        LocalDate created = LocalDate.now().minusDays(9);
        LocalDate expire = LocalDate.now().plusDays(11);
        Food butter = new Butter("Butter", expire, created, 120, 1);
        assertThat(controlQuality.checkQuality(butter)).isTrue();
    }

    @Test
    void whenAddThenGoesToTrashTrue() throws ParseException {
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStore(new Trash());
        LocalDate created = LocalDate.now().minusDays(14);
        LocalDate expire = LocalDate.now();
        Food sausage = new Sausage("Sausage", expire, created,120, 1);
        assertThat(controlQuality.checkQuality(sausage)).isTrue();
    }
}