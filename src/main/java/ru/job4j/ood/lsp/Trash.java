package ru.job4j.ood.lsp;


public class Trash extends AbstractStore {
    CheckDate checkDate = new CheckDate();

    @Override
    public boolean checkState(Food food) {
        boolean rsl = false;
        int percent = checkDate.percent(food.getCreateDate(), food.getExpiryDate());
        if (percent == 100) {
            rsl = true;
        }
        return rsl;
    }
}
