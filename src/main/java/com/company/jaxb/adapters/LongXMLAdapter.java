package com.company.jaxb.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LongXMLAdapter extends XmlAdapter<String, Long> {
    
    @Override public String marshal(Long id) {
        return (id == null) ? "" : id.toString();
    }
    
    @Override public Long unmarshal(String id) {
        return Long.parseLong(id);
    }
}
