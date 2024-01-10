package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class EmployeeMain {
    public static void main(String[] args) throws Exception {
        /*final Employee employee = new Employee("Vasya", 40, true, new Card(65664),
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
        System.out.println(employeeMod);*/
        Employee employee = new Employee("Vasya", 40, true, new Card(65664),
                new String[] {"IT", "Java junior developer"});
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Employee result = (Employee) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}