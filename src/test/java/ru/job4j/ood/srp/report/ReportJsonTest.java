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
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        ReportJson json = new ReportJson(store, parser);
        String expected = "[\n"
                +  "  {\n"
                +  "    \"fired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"name\": \"Ivan\",\n"
                +  "    \"hired\": \"" + parser.parse(now) + "\",\n"
                +  "    \"salary\": 100.0\n"
                +  "  }\n"
                +  "]";
        assertThat(json.generate(employee -> true)).isEqualTo(expected.toString());
    }

}