package ru.job4j.ood.srp;

import java.awt.print.Book;
import java.util.List;

public class AcceptNewBook implements BookShop {
    @Override
    public void acceptNewBook(int count, String title, String author) {
    }
    @Override
    public List<Book> inStock() {
        return null;
    }

    @Override
    public void writingOffDamagedBooks(String title) {
    }
}
