package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public final String pathPhrases = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    public final String separator = System.lineSeparator();
    private static final List<Comment> COMMENTS = new ArrayList<>();
    public final Integer count = 50;
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(pathPhrases);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static List<Comment> getComments() {
        return COMMENTS;
    }

    @Override
    public void generate() {
        COMMENTS.clear();
        int count1 = count;
        for (int i = 0; i < count1; i++) {
            String comment = String.format("%s%s%s%s%s",
                    phrases.get(random.nextInt(phrases.size())), separator,
                    phrases.get(random.nextInt(phrases.size())), separator,
                    phrases.get(random.nextInt(phrases.size())));
            COMMENTS.add(new Comment(comment,
                    userGenerator.randomUser()));
        }
    }
}