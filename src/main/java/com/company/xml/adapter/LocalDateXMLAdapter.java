package com.company.xml.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateXMLAdapter extends XmlAdapter<String, LocalDate> {
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    @Override public LocalDate unmarshal(String v) {
        return LocalDate.from(formatter.parse(v));
    }
    
    @Override public String marshal(LocalDate v) {
        return formatter.format(v);
    }
}

