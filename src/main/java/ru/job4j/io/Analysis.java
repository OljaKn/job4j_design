package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(target)) {
            String line;
            boolean isUnavailable = false;
            String startTime = "";
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(" ");
                String status = str[0];
                if (("400".equals(status) || "500".equals(status)) && !isUnavailable) {
                    isUnavailable = true;
                    startTime = str[1];
                    writer.append(startTime).append(";");
                }
                if (("200".equals(status) || "300".equals(status)) && isUnavailable) {
                    isUnavailable = false;
                    startTime = str[1];
                    writer.append(startTime).append(";").append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}