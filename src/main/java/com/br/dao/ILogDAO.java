package com.br.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author tetzner
 */
public interface ILogDAO {
    void inserirLog(String mensagem);
    List<String> buscarTodosLogs();
    Optional<String> buscarLogPorID(int id);
}
