package com.br.adapter;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 *
 * @author tetzner
 */
public class JacksonAdapter implements IJsonSerializer, IXMLSerializer {

    @Override
    public String serializarJson(Object object) {
        
        String json = null;
        
        try {
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            
            objectMapper.registerModule(new JavaTimeModule());
            
            // Serializa em json
            json = objectMapper.writeValueAsString(object);
            
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro de serialização em JSON" + e.getMessage(), e);
        }
        
        return json;
    }
    
    @Override
    public String serializarXML(Object object) {

        String xml = null;

        try {

            XmlMapper xmlMapper = new XmlMapper();
            
            xmlMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            
            xmlMapper.registerModule(new JavaTimeModule());
            
            // Serializa em xml
            xml = xmlMapper.writeValueAsString(object);

        } catch (Exception e) {
            throw new RuntimeException("Erro de serialização em XML" + e.getMessage(), e);
        }
        
        return xml;
    }

}
