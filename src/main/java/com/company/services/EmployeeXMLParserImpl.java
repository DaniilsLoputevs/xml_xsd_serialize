package com.company.services;

import com.company.models.Employee;
import com.company.xml.adapter.Employees;
import com.company.xml.adapter.LocalDateXMLAdapter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.SneakyThrows;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Collection;

public class EmployeeXMLParserImpl implements EmployeeXMLParser {
    /**
     * Возможен dependency injection в случаи Spring
     */
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;
    
    @SneakyThrows
    public EmployeeXMLParserImpl() {
        var jaxbContext = JAXBContext.newInstance(Employees.class);
        this.marshaller = jaxbContext.createMarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        this.unmarshaller = JAXBContext.newInstance(Employees.class).createUnmarshaller();
//        this.unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setAdapter(new LocalDateXMLAdapter());
    }
    
    @SneakyThrows
    @Override public String serialize(Collection<Employee> employees) {
        var str = new StringWriter();
        marshaller.marshal(new Employees(employees), str);
        return str.toString();
    }
    
    @SneakyThrows
    @Override public Collection<Employee> deserialize(String employees) {
        return ((Employees) unmarshaller.unmarshal(new StringReader(employees))).getEmployees();
    }
    
}
