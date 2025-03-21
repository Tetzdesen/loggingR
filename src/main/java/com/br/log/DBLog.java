package com.br.log;

import com.br.dao.LogSQLiteDAO;

/**
 *
 * @author tetzner
 */
public class DBLog implements ILog {

    private final LogSQLiteDAO logDAO;

    public DBLog() {
        this.logDAO = new LogSQLiteDAO();
    }

    @Override
    public void escrever(String mensagem) {
        logDAO.inserirLog(mensagem);
    }

}
