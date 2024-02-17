package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportJsonTest {
    @Test
    public void whenOldGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 150);
        Employee worker3 = new Employee("Olja", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportJson json = new ReportJson(store, parser);
        String expected = "[\n"
                +  "  {\n"
                +  "    \"fired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"name\": \"Ivan\",\n"
                +  "    \"hired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"salary\": 100.0\n"
                +  "  },\n"
                +  "  {\n"
                +  "    \"fired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"name\": \"Petya\",\n"
                +  "    \"hired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"salary\": 150.0\n"
                +  "  },\n"
                +  "  {\n"
                +  "    \"fired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"name\": \"Olja\",\n"
                +  "    \"hired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"salary\": 200.0\n"
                +  "  }\n"
                +  "]";
        assertThat(json.generate(employee -> true)).isEqualTo(expected.toString());
    }

}