package ru.job4j.kiss.fool;

import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

    public class Fool {
        public static void main(String[] args) {
            Fool fool = new Fool();
            System.out.println("Игра FizzBuzz.");
            var startAt = 1;
            var input = new Scanner(System.in);
            while (startAt < 100) {
                System.out.println(fool.validate(startAt));
                startAt++;
                var answer = input.nextLine();
                if (!fool.validate(startAt).equals(answer)) {
                    System.out.println("Ошибка. Начинай снова.");
                    startAt = 0;
                }
                startAt++;
            }
        }

        public static String validate(int startAt) {
            String rsl = "";
            if (startAt % 3 == 0 && startAt % 5 == 0) {
                rsl = "FizzBuzz";
            } else if (startAt % 3 == 0) {
                rsl = "Fizz";
            } else if (startAt % 3 == 0) {
                rsl = "Buzz";
            }
            return rsl;
        }
    }