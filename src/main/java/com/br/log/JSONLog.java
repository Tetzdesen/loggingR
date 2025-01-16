package com.br.log;

import com.br.adapter.JacksonAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author tetzner
 */
public class JSONLog implements ILog {

    private final File arquivoJSON;
    private static final String CAMINHOARQUIVO = "logs/JSONLog.json";
    private final JacksonAdapter jacksonAdapter;

    public JSONLog() {
        this.arquivoJSON = criarArquivo();
        this.jacksonAdapter = new JacksonAdapter();
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
    public void escrever(Object object) {
        try {
            String mensagem = jacksonAdapter.serializarJson(object);
            escreverMensagemEmArquivoJSON(mensagem);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao realizar a escrita da mensagem: " + e.getMessage(), e);
        }
    }

    private void escreverMensagemEmArquivoJSON(String mensagem) throws IOException {
        try {

            StringBuilder conteudoAtual = new StringBuilder();

            if (arquivoJSON.length() > 2) {

                try (BufferedReader reader = new BufferedReader(new FileReader(arquivoJSON))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        conteudoAtual.append(linha);
                    }
                }

                int posicaoFechamento = conteudoAtual.lastIndexOf("]");

                if (posicaoFechamento != -1) {
                    conteudoAtual.deleteCharAt(posicaoFechamento);
                    conteudoAtual.append(",").append(mensagem).append("]");
                }

            } else {
                conteudoAtual.append("[").append(mensagem).append("]");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoJSON))) {
                writer.write(conteudoAtual.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo JSON: " + e.getMessage(), e);
        }
    }

}
