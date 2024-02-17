package ru.job4j.ood.srp.report;


import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportXml implements Report {
    private final Store store;
    public ReportXml(Store store) {
        this.store = store;
    }
    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        StringBuilder sb = new StringBuilder();
        Employees employees = new Employees(store.findBy(filter));
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(employees, writer);
            sb.append(writer.getBuffer().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }
    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Employees {
        private List<Employee> employees;
        public Employees() {

        }
        public Employees(List<Employee> employees) {
            this.employees = employees;
        }
        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    public static class DateAdapterXml extends XmlAdapter<String, Calendar> {
        private static final ThreadLocal<DateFormat> DATE_FORMAT
                = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd:MM:yyyy HH:mm"));

        @Override
        public Calendar unmarshal(String d) throws ParseException {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DATE_FORMAT.get().parse(d));
            return cal;
        }

        @Override
        public String marshal(Calendar d) {
            return DATE_FORMAT.get().format(d.getTime());
        }
    }
}
