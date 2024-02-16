package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.generics.MemStore;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportXmlTest {
   @Test
    void wheGenerateXmlReportWithTwoPeople() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
       String formattedDate = dateFormat.format(now.getTime());
        Employee employee1 = new Employee("Ivan", now, now, 100);
        Employee employee2 = new Employee("Olja", now, now, 50);
        store.add(employee1);
        store.add(employee2);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        ReportXml report = new ReportXml(new MemoryStore());
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                +  "<employees>\n"
                +  "    <employee>\n"
                +  "        <name>Ivan</name>\n"
                +  "        <hired>" + parser.parse(now) + "</hired>\n"
                +  "        <fired>" + parser.parse(now) + "</fired>\n"
                +  "        <salary>100.0</salary>\n"
                +  "    </employee>\n"
                +  "    <employee>\n"
                +  "        <name>Anna</name>\n"
                +  "        <hired>" + parser.parse(now) + "</hired>\n"
                +  "        <fired>" + parser.parse(now) + "</fired>\n"
                +  "        <salary>200.0</salary>\n"
                +  "    </employee>\n"
                +  "</employees>\n";
        assertThat(report.generate(employee -> true)).isEqualTo(expected);
    }
}