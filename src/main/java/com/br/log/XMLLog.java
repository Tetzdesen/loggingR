package com.br.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.br.adapter.JacksonAdapter;

/**
 *
 * @author tetzner
 */
public class XMLLog implements ILog {

    private final File arquivoXML;
    private static final String CAMINHOARQUIVO = "logs/XMLLog.xml";
    private final JacksonAdapter jacksonAdapter;

    public XMLLog() {
        jacksonAdapter = new JacksonAdapter();
        arquivoXML = criarArquivoXML();
    }

    private File criarArquivoXML() {

        File arquivo = new File(CAMINHOARQUIVO);
        arquivo.getParentFile().mkdirs();
        if (!arquivo.exists() || arquivo.length() == 0) {
            try {
                arquivo.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                    writer.write(obterCabecalhoXML());
                    writer.write("<Registros>\n");
                    writer.write("</Registros>");
                }
            } catch (IOException e) {
                throw new RuntimeException("Erro na criação de arquivo XML" + e.getMessage(), e);
            }
        }
        return arquivo;
    }

    @Override
    public void escrever(Object object) {
        try {
            String xml = jacksonAdapter.serializarXML(object);
            escreverMensagemEmArquivoXML(xml);
            System.out.println("\nLog registrado no arquivo XML!");
        } catch (IOException e) {
            throw new RuntimeException("Erro na gravação de arquivo XML" + e.getMessage(), e);
        }
    }

    private void escreverMensagemEmArquivoXML(String mensagem) throws IOException {

        StringBuilder conteudoXML = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoXML))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudoXML.append(linha).append("\n");
            }
        }

        int posicaoFechamento = conteudoXML.lastIndexOf("</Registros>");

        if (posicaoFechamento != -1) {
            conteudoXML.delete(posicaoFechamento, conteudoXML.length());
            conteudoXML.append("  ").append(mensagem).append("\n");
            conteudoXML.append("</Registros>");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoXML))) {
            writer.write(conteudoXML.toString());
        }
    }

    private String obterCabecalhoXML() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    }
}
