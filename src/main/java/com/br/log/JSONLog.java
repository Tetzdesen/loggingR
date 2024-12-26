package com.br.log;

import com.br.adapter.GsonAdapter;
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
    private final String caminhoArquivo;
    private final GsonAdapter gsonAdapter;

    public JSONLog() {
        this.caminhoArquivo = "logs/JSONLog.json";
        this.gsonAdapter = new GsonAdapter();
    }

    @Override
    public void escrever(Object object) {
        try {
            String mensagem = gsonAdapter.serializar(object);
            registrarEmArquivo(mensagem);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao realizar a escrita da mensagem: " + e.getMessage(), e);
        }
    }
    
    private void registrarEmArquivo(String mensagem) throws IOException {
        File file = new File(caminhoArquivo);
        try { 
            
            StringBuilder conteudoAtual = new StringBuilder();

            if (file.length() > 2) {

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        conteudoAtual.append(linha);
                    }
                }

                int posicaoFechamento = conteudoAtual.lastIndexOf("]");

                if (posicaoFechamento != -1) {
                    conteudoAtual.deleteCharAt(posicaoFechamento);
                    conteudoAtual.append(",\n").append(mensagem).append("\n]");
                }

            } else {
                conteudoAtual.append("[\n").append(mensagem).append("\n]");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(conteudoAtual.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo JSON: " + e.getMessage(), e);
        }
    }
    
}
