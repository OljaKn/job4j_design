package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TrashTest {
    Trash trash = new Trash();
    @Test
    void whenTrashIsTrue() {
        LocalDate created = LocalDate.now().minusDays(15);
        LocalDate expire = LocalDate.now().plusDays(0);
        Food sausage = new Sausage("Sausage", expire, created, 100, 10);
        assertThat(trash.checkState(sausage)).isTrue();
    }

}