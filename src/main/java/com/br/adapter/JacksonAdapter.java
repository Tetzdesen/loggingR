package com.br.adapter;

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
            
            objectMapper.registerModule(new JavaTimeModule());
            
            // Serializar
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
            
            xmlMapper.registerModule(new JavaTimeModule());
            
            // Serializa
            xml = xmlMapper.writeValueAsString(object);

        } catch (Exception e) {
            throw new RuntimeException("Erro de serialização em XML" + e.getMessage(), e);
        }
        
        return xml;
    }

}
