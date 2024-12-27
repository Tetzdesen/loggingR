package com.br.principal;

import com.br.log.DBLog;
import com.br.log.ILog;

/**
 *
 * @author tetzner
 */
public class Principal {

    public static void main(String[] args) {
        ILog logService = new DBLog();
        Pessoa pessoa = new Pessoa("João", 30, "Nova Iorque");
        logService.escrever(pessoa);
    }
}
