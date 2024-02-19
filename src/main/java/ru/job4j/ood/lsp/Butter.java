package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Butter extends Food {
    public Butter(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
