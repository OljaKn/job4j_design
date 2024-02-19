package ru.job4j.ood.lsp;

import java.text.ParseException;
import java.util.List;

public interface Store {
    boolean add(Food food) throws ParseException;
    List<Food> findAll();
    boolean checkState(Food food) throws ParseException;
}
