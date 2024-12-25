package com.br.log;

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
public class FileService {
    private final File file;

    public FileService(String caminhoArquivo) {
        this.file = new File(caminhoArquivo);
    }

    public void escreverNoArquivo(String mensagem) throws IOException {

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
