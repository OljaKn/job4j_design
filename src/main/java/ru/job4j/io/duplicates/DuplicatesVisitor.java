package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> propertyMap;
    private List<FileProperty> paths;

    public DuplicatesVisitor() {
        this.propertyMap = new HashMap<>();
        this.paths = new ArrayList<>();
    }

    public List<FileProperty> getPaths() {
        return paths;
    }
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
       if (!propertyMap.containsKey(fileProperty)) {
           paths.add(fileProperty);
       } else {
           propertyMap.put(fileProperty, file);
       }
        return super.visitFile(file, attributes);
    }
}