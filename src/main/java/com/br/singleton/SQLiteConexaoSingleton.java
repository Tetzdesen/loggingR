package com.br.singleton;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            verificaArquivoDB();
            conexaoSQLite = DriverManager.getConnection(URL);
            criaTabelaRegistro();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco SQLite" + e.getMessage(), e);
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

    private void criaTabelaRegistro() {
        String sql = """
            CREATE TABLE IF NOT EXISTS log (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                registro VARCHAR(500) NOT NULL
            );
        """;

        try (var stmt = conexaoSQLite.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na criação de tabela no banco de dados: " + e.getMessage(), e);
        }
    }

    private void verificaArquivoDB() {
        File arquivo = new File("db/log.db");
        arquivo.getParentFile().mkdirs();
        if (!arquivo.exists() || arquivo.length() == 0) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar arquivo: " + e.getMessage(), e);
            }
        }
    }

    public void closeConnection() {
        if (conexaoSQLite != null) {
            try {
                conexaoSQLite.close();
                System.out.println("Conexão SQLite fechada.");
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão SQLite: " + e.getMessage(), e);
            }
        }
    }

}
