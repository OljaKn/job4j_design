package ru.job4j.ood.srp;

import java.awt.print.Book;
import java.util.List;

public interface BookShop {
    Book BOOK = new Book();
    void acceptNewBook(int count, String title, String author);
    /* оприходовать поступление новых книг*/
    List<Book> inStock();
    /* получить список книг, имеющихся в магазине*/
    void writingOffDamagedBooks(String title);
    /* списание порченных книг*/
}
