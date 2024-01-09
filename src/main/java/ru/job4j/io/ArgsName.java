package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

        private final Map<String, String> values = new HashMap<>();

        public String get(String key) {
            if (!values.containsKey(key)) {
                throw new IllegalArgumentException("This key: " + key + " is missing");
            }
            return values.get(key);
        }

        private void parse(String[] args) {
            if (args.length == 0) {
                throw new IllegalArgumentException();
            }
            for (String arg : args) {
                if (validate(arg)) {
                    String[] parts = arg.replaceFirst("-", "").split("=");
                    String key = parts[0];
                    String value = parts[1];
                    values.put(key, value);
                }
            }
        }

            public static ArgsName of(String[]args) {
                ArgsName names = new ArgsName();
                names.parse(args);
                return names;
            }

            private boolean validate(String args) {
                return args.matches("-.*=.*");
            }

            public static void main(String[]args) {
                ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
                System.out.println(jvm.get("Xmx"));

                ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
                System.out.println(zip.get("out"));
            }
}