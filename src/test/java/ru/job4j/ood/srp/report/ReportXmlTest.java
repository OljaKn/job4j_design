package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.generics.MemStore;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.MemoryStore.*;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import javax.xml.bind.JAXBException;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.assertThat;
class ReportXmlTest {
   @Test
    void wheGenerateXmlReportWithTwoPeople() throws JAXBException {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee employee1 = new Employee("Ivan", now, now, 100);
        Employee employee2 = new Employee("Olja", now, now, 50);
        store.add(employee1);
        store.add(employee2);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        ReportXml report = new ReportXml(store);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                +  "<employees>\n"
                +  "    <employee>\n"
                +  "        <name>Ivan</name>\n"
                +  "        <hired>" + parser.parse(now) + "</hired>\n"
                +  "        <fired>" + parser.parse(now) + "</fired>\n"
                +  "        <salary>100.0</salary>\n"
                +  "    </employee>\n"
                +  "    <employee>\n"
                +  "        <name>Olja</name>\n"
                +  "        <hired>" + parser.parse(now) + "</hired>\n"
                +  "        <fired>" + parser.parse(now) + "</fired>\n"
                +  "        <salary>50.0</salary>\n"
                +  "    </employee>\n"
                +  "</employees>\n";
        assertThat(report.generate(employee -> true)).isEqualTo(expected);
    }
}