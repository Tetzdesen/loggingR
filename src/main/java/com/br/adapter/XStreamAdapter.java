package com.br.adapter;

import com.thoughtworks.xstream.XStream;

/**
 *
 * @author tetzner
 */
public class XStreamAdapter implements IXMLFormatter {
    
    private final XStream xstream;

    public XStreamAdapter() {
        this.xstream = new XStream();
    }

    @Override
    public String retornarXMLFormatado(String mensagem) {
        return xstream.toXML(mensagem);
    }

}
