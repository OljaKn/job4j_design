package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("data/log.txt"))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] worlds = line.split(" ");
                if (worlds.length >= 2 && "404".equals(worlds[worlds.length - 2])) {
                    rsl.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public void saveTo(String out) {
        List<String> data = filter();
        try (PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter(out)))) {
            for (int i = 0; i < data.size(); i++) {
                bw.printf("%s%n", data.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }

}