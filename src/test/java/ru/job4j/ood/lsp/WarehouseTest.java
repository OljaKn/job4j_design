package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class WarehouseTest {
    Warehouse warehouse = new Warehouse();
    @Test
    void whenWarehouseIsTrue() {
        LocalDate created = LocalDate.now().minusDays(5);
        LocalDate expire = LocalDate.now().plusDays(20);
        Food juice = new Juice("Juice", expire, created, 100, 10);
        assertThat(warehouse.checkState(juice)).isTrue();
    }

}