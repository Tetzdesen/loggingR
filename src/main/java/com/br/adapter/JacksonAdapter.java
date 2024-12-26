package com.br.adapter;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 *
 * @author tetzner
 */
public class JacksonAdapter implements IJsonSerializer {

    @Override
    public String serializar(Object object) {
        String xml = null;
        try {
            XmlMapper xmlMapper = new XmlMapper();

            xml = xmlMapper.writeValueAsString(object);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return xml;
    }

}
