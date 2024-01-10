package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMain {
    public static void main(String[] args) throws Exception {
        JSONObject jsonCard = new JSONObject("{\"numberCard\":65664}");

        List<String> list = new ArrayList<>();
        list.add("IT");
        list.add("Java junior developer");
        JSONArray jsonFunction = new JSONArray(list);

        final Employee employee = new Employee("Vasya", 40, true, new Card(65664),
                new String[] {"IT", "Java junior developer"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", employee.getName());
        jsonObject.put("age", employee.getAge());
        jsonObject.put("probation", employee.getProbation());
        jsonObject.put("numberCard", jsonCard);
        jsonObject.put("function", jsonFunction);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(employee).toString());
    }
}