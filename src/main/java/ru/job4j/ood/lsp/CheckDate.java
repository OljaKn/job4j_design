package ru.job4j.ood.lsp;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CheckDate {
    public static int percent(LocalDate create, LocalDate expiry) {
        double a = ChronoUnit.DAYS.between(LocalDate.now(), create);
        double b = ChronoUnit.DAYS.between(expiry, create);
        int rsl = (int) (a / b * 100);
        return rsl;
    }
}
