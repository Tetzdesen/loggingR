package com.br.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.br.adapter.OpenCSVAdapter;

public class CSVLog implements ILog {

    private final File arquivoCSV;
    private static final String CAMINHOARQUIVO = "logs/CSVLog.csv";
    private final OpenCSVAdapter adapterCSV;

    public CSVLog() {
        this.arquivoCSV = criarArquivo();
        this.adapterCSV = new OpenCSVAdapter();
    }

    private File criarArquivo() {
        File arquivo = new File(CAMINHOARQUIVO);
        arquivo.getParentFile().mkdirs();
        if (!arquivo.exists() || arquivo.length() == 0) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar arquivo: " + e.getMessage(), e);
            }
        }
        return arquivo;
    }

    @Override
    public void escrever(String mensagem) {
        try {
            String csv = adapterCSV.retornarCSVFormatado(mensagem);
            escreverMensagemEmArquivoCSV(csv);
        } catch (Exception e) {
            throw new RuntimeException("Erro na gravação de arquivo CSV" + e.getMessage(), e);
        }
    }

    private void escreverMensagemEmArquivoCSV(String mensagem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV, true))) {
            writer.write(mensagem);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo CSV: " + e.getMessage(), e);
        }
    }
    
}
