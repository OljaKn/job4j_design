package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        /*LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");*/
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);

        byte b = 12;
        short sh = 123;
        int in = -123;
        long l = 4748588;
        float f = 75.5f;
        double d = 12.54;
        char ch = 'a';
        boolean boo = true;
        LOG.debug("Log info byte : {}, short : {}, int : {}, long : {},"
                        + " float : {}, double : {}, char : {}, boolean : {}",
                b, sh, in, l, f, d, ch, boo);
    }
}