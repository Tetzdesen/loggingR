package com.br.dao;

import com.br.singleton.SQLiteConexaoSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author tetzner
 */
public class LogSQLiteDAO implements ILogDAO {

    @Override
    public void inserirLog(String mensagem) {

        Connection conexao = SQLiteConexaoSingleton.getInstance().getconexaoSQLite();

        String sql = "INSERT INTO log (registro) VALUES (?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, mensagem);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir log" + e.getMessage(), e);
        }

    }

    @Override
    public List<String> buscarTodosLogs() {

        Connection conexao = SQLiteConexaoSingleton.getInstance().getconexaoSQLite();

        String sql = "SELECT * FROM log";

        List<String> logs = new ArrayList<>();

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String mensagem = rs.getString("registro");
                logs.add(mensagem);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os logs: " + e.getMessage(), e);
        }
        
        return logs;
    }

    @Override
    public Optional<String> buscarLogPorID(int id) {

        Connection conexao = SQLiteConexaoSingleton.getInstance().getconexaoSQLite();

        String sql = "SELECT * FROM log WHERE id = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String mensagem = rs.getString("registro");
                    return Optional.of(mensagem);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar log pelo ID" + e.getMessage(), e);
        }
    }
}
