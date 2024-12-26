package com.br.principal;

import com.br.log.JSONLog;
import com.br.log.LogService;
import com.br.log.XMLLog;

/**
 *
 * @author tetzner
 */
public class Principal {

    public static void main(String[] args) {
        LogService logService = new LogService(new XMLLog());
        Pessoa pessoa = new Pessoa("João", 30, "Nova Iorque");
        logService.escreverMensagem(pessoa);
    }
}
