package com.br.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tetzner
 */
public class SQLiteConexaoSingleton {

    private static volatile SQLiteConexaoSingleton instanciaUnica = null;
    private static final String URL = "jdbc:sqlite:db/log.db";
    private Connection conexaoSQLite;

    private SQLiteConexaoSingleton() {

        try {
            conexaoSQLite = DriverManager.getConnection(URL);
            criarTabelaRegistro(conexaoSQLite);
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteConexaoSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static SQLiteConexaoSingleton getInstance() {
        if (instanciaUnica == null) {
            synchronized (SQLiteConexaoSingleton.class) {
                instanciaUnica = new SQLiteConexaoSingleton();
            }
        }
        return instanciaUnica;
    }

    public Connection getconexaoSQLite() {
        return conexaoSQLite;
    }

    private void criarTabelaRegistro(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS log (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                registro VARCHAR(500) NOT NULL
            );
        """;

        try (var stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void closeConnection() {
        if (conexaoSQLite != null) {
            try {
                conexaoSQLite.close();
                System.out.println("Conexão SQLite fechada.");
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão SQLite: " + ex.getMessage());
            }
        }
    }

}
