package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public static final String DIRECTORY = "Укажите кэшируемую директорию";
    public static final int LOAD_CACHE = 1;
    public static final int GET_CACHE = 2;

    public static final String MENU = """
                Введите 1, чтобы загрузить содержимое файла в кэш.
                Введите 2, чтобы получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.println(MENU);
            int user = Integer.parseInt(scanner.nextLine());
            if  (LOAD_CACHE == user) {
                System.out.println(DIRECTORY);
                String dir = scanner.nextLine();
                System.out.println("Введите имя файла для загрузки в кэш");
                String name = scanner.nextLine();
                new DirFileCache(dir).get(name);
            } else if (GET_CACHE == user) {
                System.out.println(DIRECTORY);
                String dir = scanner.nextLine();
                System.out.println("Введите имя файла для получения его содержимого из кэша");
                String name = scanner.nextLine();
                System.out.println(new DirFileCache(dir).get(name));
            } else {
                run = false;
                System.out.println("Конец работы");
            }
        }
    }
}
