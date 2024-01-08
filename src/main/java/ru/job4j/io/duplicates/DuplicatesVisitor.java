package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> propertyMap;

    public DuplicatesVisitor() {
        this.propertyMap = new HashMap<>();
    }

    public List<Path> getPaths() {
        List<Path> rsl = new ArrayList<>();
        for (List<Path> list: propertyMap.values()) {
            if (list.size() > 1) {
                rsl.addAll(list);
            }
        }
        return rsl;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        List<Path> duplicate = new ArrayList<>();
        if (!propertyMap.containsKey(fileProperty)) {
            propertyMap.put(fileProperty, duplicate);
            duplicate.add(file.toAbsolutePath());
        } else {
            propertyMap.get(fileProperty).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attributes);
    }
}