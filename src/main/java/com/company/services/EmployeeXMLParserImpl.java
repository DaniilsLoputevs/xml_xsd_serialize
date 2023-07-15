package com.company.services;

import com.company.jaxb.adapters.LocalDateXMLAdapter;
import com.company.jaxb.schema.StringSchemaOutputResolver;
import com.company.jaxb.wrappers.Employees;
import com.company.models.Employee;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.SneakyThrows;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

public class EmployeeXMLParserImpl implements EmployeeXMLParser {
    /**
     * Возможен dependency injection в случаи Spring
     */
    private final JAXBContext jaxbContext;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;
    
    @SneakyThrows
    public EmployeeXMLParserImpl() {
        this.jaxbContext = JAXBContext.newInstance(Employees.class);
        this.marshaller = jaxbContext.createMarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        this.unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setAdapter(new LocalDateXMLAdapter());
    }
    
    @SneakyThrows
    @Override public String serialize(Collection<Employee> employees) {
        var str = new StringWriter();
        var wrapper = new Employees();
        wrapper.setEmployees(employees);
        marshaller.marshal(wrapper, str);
        return str.toString();
    }
    
    @SneakyThrows
    @Override public Collection<Employee> deserialize(String employees) {
        return ((Employees) unmarshaller.unmarshal(new StringReader(employees))).getEmployees();
    }
    
    @SneakyThrows
    @Override public String generateSchemaForSupportedClass() {
        var schemaOutputResolver = new StringSchemaOutputResolver();
        jaxbContext.generateSchema(schemaOutputResolver);
        return schemaOutputResolver.getContent();
    }
    
}
