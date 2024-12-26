package com.br.log;

import com.br.singleton.SQLiteConexaoSingleton;
import java.sql.SQLException;

/**
 *
 * @author tetzner
 */
public class DBLog implements ILog {

    @Override
    public void escrever(Object object) {
        try {
            SQLiteConexaoSingleton conexao = SQLiteConexaoSingleton.getInstance();
            conexao.getconexaoSQLite().createStatement().execute(object.toString());
            System.out.println("\nDado registrado com sucesso!");
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao registrar dado: " + e.getMessage());
        }
    }

}
