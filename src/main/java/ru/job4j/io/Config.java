package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                        .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                        .map(el -> el.split("=", 2))
                        .filter(this::checkCondition)
                        .forEach(el -> values.put(el[0], el[1]));
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkCondition(String[] keyValue) {
        if (keyValue.length != 2 || keyValue[0].length() == 0 || keyValue[1].length() == 0) {
            throw  new IllegalArgumentException();
        }
        return true;
    }

    public String value(String key) {
        String rsl = null;
        if (values.containsKey(key)) {
            rsl = values.get(key);
        }
        return rsl;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/pair_with_comment.properties"));
    }
}