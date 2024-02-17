package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    final Gson gson;

    public ReportJson(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (Employee employee : store.findBy(filter)) {
            jsonObject = new JSONObject();
            jsonObject.put("name", employee.getName());
            jsonObject.put("hired", dateTimeParser.parse(employee.getHired()));
            jsonObject.put("fired", dateTimeParser.parse(employee.getFired()));
            jsonObject.put("salary", employee.getSalary());
            jsonArray.put(jsonObject);

        }
        return gson.toJson(jsonArray.toList());
    }
}
