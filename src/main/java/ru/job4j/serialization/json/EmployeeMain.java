package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmployeeMain {
    public static void main(String[] args) {
        final Employee employee = new Employee("Vasya", 40, true, new Card(65664),
                new String[] {"IT", "Java junior developer"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(employee));

        final String employeeJson =
                "{"
                        + "\"name\":Petya,"
                        + "\"age\":30,"
                        + "\"probation\":false,"
                        + "\"card\":"
                        + "{"
                        + "\"numberCard\":65578788"
                        + "},"
                        + "\"function\":"
                        + "[\"IT\",\"Senior\"]"
                        + "}";

        final Employee employeeMod = gson.fromJson(employeeJson, Employee.class);
        System.out.println(employeeMod);
    }
}