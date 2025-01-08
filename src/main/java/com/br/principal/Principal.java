package com.br.principal;

import com.br.log.DBLog;
import com.br.log.ILog;
import com.br.log.JSONLog;
import com.br.log.XMLLog;
import com.google.gson.GsonBuilder;

/**
 *
 * @author tetzner
 */
public class Principal {

    public static void main(String[] args) {
        ILog logService = new JSONLog();
        Pessoa pessoa = new Pessoa("João", 30, "Nova Iorque");
        logService.escrever(pessoa);
    }
}
