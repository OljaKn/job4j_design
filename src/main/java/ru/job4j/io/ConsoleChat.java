package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> botAnswersList = new ArrayList<>();
    private List<String> logList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> botAnswersList = readPhrases(botAnswers);
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("  Добро пожаловать в чат! ");
        boolean continueChat = true;
        boolean flag = false;
        while (continueChat) {
            String user = scanner.nextLine();
            logList.add("User: " + user);
            if (OUT.equalsIgnoreCase(user)) {
                continueChat = false;
                break;
            } else if (STOP.equalsIgnoreCase(user)) {
                flag = true;
            } else if (CONTINUE.equalsIgnoreCase(user)) {
                flag = false;
                String bot = botAnswersList.get(random.nextInt(botAnswersList.size()));
                logList.add("Bot: " + bot);
                System.out.println(bot);
            } else {
                if (!flag) {
                    String bot = botAnswersList.get(random.nextInt(botAnswersList.size()));
                    logList.add("Bot: " + bot);
                    System.out.println(bot);
                }
            }
        }
        saveLog(logList, path);
    }

    private List<String> readPhrases(String botAnswers) {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            reader.lines()
                    .map(string -> string + System.lineSeparator())
                    .forEach(botAnswersList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botAnswersList;
    }

    private void saveLog(List<String> log, String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat consoleChat = new ConsoleChat("data/history.log", "data/botAnswer.txt");
        consoleChat.run();
    }
}