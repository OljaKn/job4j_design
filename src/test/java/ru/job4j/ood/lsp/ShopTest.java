package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ShopTest {
    Shop shop = new Shop();
    @Test
    void whenShopIsTrue() {
        LocalDate created = LocalDate.now().minusDays(6);
        LocalDate expire = LocalDate.now().plusDays(4);
        Food butter = new Butter("Butter", expire, created, 100, 10);
        assertThat(shop.checkState(butter)).isTrue();
    }

}