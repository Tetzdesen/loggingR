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

  
}
