package ru.job4j.ood.lsp;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class ControlQuality {
    List<Store> store = new ArrayList<>();

    public boolean addStore(Store store) {
        return this.store.add(store);
    }

    public boolean checkQuality(Food food) throws ParseException {
        boolean rsl = false;
        for (Store store : store) {
            if (store.add(food)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }
    public void resort(Food food) throws ParseException {
        List<Food> newList = new ArrayList<>();
        for (Store store: store) {
            newList.addAll(store.findAll());
            store.findAll().clear();
        }
        for (Food foods: newList) {
            checkQuality(food);
        }
    }
}

