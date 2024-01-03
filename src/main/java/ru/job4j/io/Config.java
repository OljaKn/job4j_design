package ru.job4j.io;

import java.io.BufferedReader;
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
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("#") && !line.startsWith("=") && !line.endsWith("=")) {
                    String[] keyValue = line.split("=", 2);
                    String key = keyValue[0];
                    String value = keyValue[1];
                    values.put(key, value);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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