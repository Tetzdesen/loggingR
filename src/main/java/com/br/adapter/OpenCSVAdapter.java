package com.br.adapter;

import java.io.StringWriter;

public class OpenCSVAdapter implements ICSVFormatter {

    @Override
    public String retornarCSVFormatado(String mensagem) {
        
        StringWriter writer = new StringWriter();

        writer.append(mensagem);

        return writer.toString();
    }

}
