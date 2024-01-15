package ru.job4j.io;

import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName arguments = ArgsName.of(args);
        String directory = arguments.get("d");
        String exclude = arguments.get("e");
        String output = arguments.get("o");

        File target = new File(output);
        File dir = new File(directory);
        List<Path> files = new Search().search(Paths.get(directory), file -> !file.toString().endsWith(exclude));

        zip.packFiles(files, target.toPath());
    }
}