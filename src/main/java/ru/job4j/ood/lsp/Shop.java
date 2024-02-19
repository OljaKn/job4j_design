package ru.job4j.ood.lsp;


public class Shop extends AbstractStore {
    CheckDate checkDate = new CheckDate();

    @Override
    public boolean checkState(Food food) {
        boolean rsl = false;
        int percent = checkDate.percent(food.getCreateDate(), food.getExpiryDate());
        if (percent > 75 && percent < 100) {
            food.setPrice(food.getPrice() - food.getPrice() * 0.2);
            rsl = true;
        }
        if (percent > 25 && percent < 75) {
            rsl = true;
        }
        return rsl;
    }
}
