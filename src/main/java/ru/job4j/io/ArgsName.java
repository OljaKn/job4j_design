package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

        private final Map<String, String> values = new HashMap<>();

        public String get(String key) {
            if (!values.containsKey(key)) {
                throw new IllegalArgumentException("This key: '" + key + "' is missing");
            }
            return values.get(key);
        }

        private void parse(String[] args) {
            if (validate(args)) {
                for (String arg : args) {

                    String[] param = arg.split("=");
                    if (param.length != 2 || param[0].isEmpty()) {
                        throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
                    } else if (param[1].isEmpty()) {
                        throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
                    }
                    values.put(param[0].replace("-", ""), param[1]);
                }
            }
        }

        private boolean validate(String[] args) {
            for (String arg : args) {
                if (!arg.startsWith("-")) {
                     throw new IllegalArgumentException("Error: This argument '" + arg + "' does not start with a '-' character");
                 }
                if (!arg.contains("=")) {
                    throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain an equal sign");
                 }
             }
                return true;
        }

        public static ArgsName of(String[]args) {
            if (args.length == 0) {
                    throw new IllegalArgumentException("Arguments not passed to program");
                }
                ArgsName names = new ArgsName();
                names.parse(args);
                return names;
            }

            public static void main(String[]args) {
                ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
                System.out.println(jvm.get("Xmx"));

                ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
                System.out.println(zip.get("out"));
            }
}