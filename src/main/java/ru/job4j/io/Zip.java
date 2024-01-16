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
private static void validateArgs(String[] args, Path dir) throws FileNotFoundException {
    if (args.length != 3) {
        throw new IllegalArgumentException();
    }
    ArgsName arguments = ArgsName.of(args);
    Set<String> argsKey = arguments.getKeys();
    for (String key: argsKey) {
        if (!"d".equals(key) && !"e".equals(key) && !"o".equals(key)) {
            throw new IllegalArgumentException();
        }
    }
    if (!Files.exists(dir)) {
        throw new FileNotFoundException();
    }

}

    public static void main(String[] args) throws IOException {
        ArgsName argsName = new ArgsName();
        Zip zip = new Zip();
        ArgsName arguments1 = ArgsName.of(args);
        String directory = arguments1.get("d");
        String exclude = arguments1.get("e");
        String output = arguments1.get("o");
        validateArgs(args, Path.of(directory));
        Search search = new Search();
        List<Path> fileList = search.search(Path.of(directory),
                p -> !p.toFile().getName().endsWith(exclude));
        zip.packFiles(fileList, Path.of(output));
    }
}