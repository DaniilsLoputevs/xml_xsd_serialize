package com.company.jaxb.schema;

import jakarta.xml.bind.SchemaOutputResolver;
import lombok.SneakyThrows;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Random;

public class StringSchemaOutputResolver extends SchemaOutputResolver {
    private final StringWriter content = new StringWriter();
    
    @SneakyThrows
    public Result createOutput(String namespaceURI, String suggestedFileName) {
        StreamResult result = new StreamResult(content);
        result.setSystemId(new Random().toString());
        return result;
    }
    
    public String getContent() {
        return content.toString();
    }
}
