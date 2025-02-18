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
    public void escrever(Object object) {
        logDAO.inserirLog(object.toString());
        System.out.println("\nDado registrado no banco SQLite com sucesso!");
    }

}
