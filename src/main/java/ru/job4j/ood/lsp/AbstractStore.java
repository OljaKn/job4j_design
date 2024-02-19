package ru.job4j.ood.lsp;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) throws ParseException {
        boolean rsl = false;
        if (checkState(food)) {
            foodList.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> findAll() {
        return foodList;
    }
}
