package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<String> rsl = new ArrayList<>();
        List<List<String>> tmp = readCSV(argsName);
        StringBuilder sb = new StringBuilder();
        List<String> header = tmp.get(0);
        List<Integer> filterIndex = new ArrayList<>();
        String[] filter = argsName.get("filter").split(",");
        for (String columnName : filter) {
            int index = header.indexOf(columnName);
            if (index != -1) {
                filterIndex.add(index);
            }
        }
        for (List<String> strings : tmp) {
            for (int index : filterIndex) {
                sb.append(strings.get(index)).append(argsName.get("delimiter"));
            }
            sb.deleteCharAt(sb.length() - 1).append(System.lineSeparator());
        }
        rsl.add(sb.toString());
        saveCSV(rsl, argsName);
    }

    private static List<List<String>> readCSV(ArgsName argsName) {
        List<String> phrases = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            scanner.useDelimiter(System.lineSeparator());
            while (scanner.hasNext()) {
                phrases.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return phrases.stream()
                .map(phrase -> List.of(phrase.split(argsName.get("delimiter"))))
                .collect(Collectors.toList());
    }

    private static void saveCSV(List<String> rsl, ArgsName argsName) {
        if ("stdout".equals(argsName.get("out"))) {
            for (String str : rsl) {
                System.out.println(str);
            }
        } else {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
                rsl.forEach(out::println);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validate(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException("Directory is not.");
        }
        if (!argsName.get("delimiter").equals(";") && !argsName.get("delimiter").equals(",")) {
            throw new IllegalArgumentException("Invalid CSV delimiter");
        }
        if (argsName.get("out").isEmpty()) {
            throw new IllegalArgumentException("Not directory");
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException("Invalid arguments filter");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments is null");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);

    }
}