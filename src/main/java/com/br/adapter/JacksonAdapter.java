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
public class JacksonAdapter implements IJSONFormatter {

    @Override
    public String retornarJSONFormatado(String mensagem) {
        
        String json = null;
        
        try {
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            
            objectMapper.registerModule(new JavaTimeModule());
            
            json = objectMapper.writeValueAsString(mensagem);
            
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro de formatação em JSON" + e.getMessage(), e);
        }
        
        return json;
    }

}
