package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportHRTest {
    @Test
    public void whenOldGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petya", now, now, 230);
        Employee worker3 = new Employee("Olja", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportHR hr = new ReportHR(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(hr.generate(employee -> true)).isEqualTo(expected.toString());
    }
}