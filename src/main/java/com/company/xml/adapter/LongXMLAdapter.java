package com.company.xml.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LongXMLAdapter extends XmlAdapter<String, Long> {
    @Override
    public String marshal(Long id)  {
        if (id == null) return "";
        return id.toString();
    }
    
    @Override
    public Long unmarshal(String id)  {
        return Long.parseLong(id);
    }
}
