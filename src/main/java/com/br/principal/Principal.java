package com.br.principal;

import com.br.log.DBLog;
import com.br.log.ILog;
import com.br.log.XMLLog;

/**
 *
 * @author tetzner
 */
public class Principal {

    public static void main(String[] args) {
        ILog logService = new XMLLog();
        Pessoa pessoa = new Pessoa("João", 30, "Nova Iorque");
        logService.escrever(pessoa);
    }
}
