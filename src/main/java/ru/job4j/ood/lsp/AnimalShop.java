package ru.job4j.ood.lsp;

public class AnimalShop {
    boolean checkColor(Animal animal) {
        if (animal.getColor() == "grey") {
            return true;
        }
        return false;
    }
}

 class Animal {
    String name;
    String color;

     public String getColor() {
         return color;
     }
 }

 class FilialAnimalShop extends AnimalShop {
     @Override
     boolean checkColor(Animal animal) {
         if (animal.getColor() == "grey") {
             throw new IllegalArgumentException("wrong color");
         } else {
             return true;
         }
     }
}