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
            if (fool.pr3and5.test(startAt)) {
                System.out.println("FizzBuzz");
            } else if (fool.pr3.test(startAt)) {
                System.out.println("Fizz");
            } else if (fool.pr5.test(startAt)) {
                System.out.println("Buzz");
            } else {
                System.out.println(startAt);
            }
            startAt++;
            var answer = input.nextLine();
            if (fool.pr3and5.test(startAt)) {
                validate(fool.predicate, "FizzBuzz", answer);
            } else if (fool.pr3.test(startAt)) {
                validate(fool.predicate, "Fizz", answer);
            } else if (fool.pr5.test(startAt)) {
                validate(fool.predicate, "Buzz", answer);
            } else {
                validate(fool.predicate, String.valueOf(startAt), answer);
            }
            startAt++;
        }
    }
    static void validate(BiPredicate pr, String text, String answer) {
        if (!pr.test(text, answer)) {
            System.out.println("Ошибка. Начинай снова.");
            int startAt = 0;
        }
     }
    BiPredicate<String, String> predicate = (text, answer) -> text.equals(answer);
    Predicate<Integer> pr3and5 = num -> num % 3 == 0 && num % 5 == 0;
    Predicate<Integer> pr3 = num -> num % 3 == 0;
    Predicate<Integer> pr5 = num -> num % 5 == 0;
}