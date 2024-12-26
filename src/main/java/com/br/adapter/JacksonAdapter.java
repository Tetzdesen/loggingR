/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
