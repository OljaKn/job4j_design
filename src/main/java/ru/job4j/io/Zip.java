package ru.job4j.io;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(output.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
private static void validateArgs(ArgsName argsName, Path dir) throws FileNotFoundException {
    /*if (argsName.length != 3) {
        throw new IllegalArgumentException();
    }*/
    Set<String> argsKey = argsName.getKeys();
    for (String key: argsKey) {
        if (!"d".equals(key) && !"e".equals(key) && !"o".equals(key)) {
            throw new IllegalArgumentException();
        }
        if ("d".equals(key) && !Path.of(argsName.get(key)).toFile().exists()) {
            throw new IllegalArgumentException("Directory is not exists");
        }
        if ("e".equals(key) && !argsName.get(key).startsWith(".")) {
            throw new IllegalArgumentException("Wrong format");
        }
        if ("o".equals(key) && !argsName.get(key).contains(".zip")) {
            throw new IllegalArgumentException("Incorrect extension");
        }
    }
    if (!Files.exists(dir)) {
        throw new FileNotFoundException();
    }
}

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName arguments1 = ArgsName.of(args);
        String directory = arguments1.get("d");
        String exclude = arguments1.get("e");
        String output = arguments1.get("o");
        validateArgs(arguments1, Path.of(directory));
        Search search = new Search();
        List<Path> fileList = search.search(Path.of(directory),
                p -> !p.toFile().getName().endsWith(exclude));
        zip.packFiles(fileList, Path.of(output));
    }
}