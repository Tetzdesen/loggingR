package com.br.log;

import com.br.adapter.GsonAdapter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tetzner
 */
public class JSONLog implements ILog {
    private final FileService fileService;
    private final GsonAdapter gsonAdapter;

    public JSONLog() {
        this.fileService = new FileService("logs/JSONLog.json");
        this.gsonAdapter = new GsonAdapter();
    }

    @Override
    public void escrever(Object object) {
        try {
            String mensagem = gsonAdapter.serializar(object);
            fileService.escreverNoArquivo(mensagem);
        } catch (IOException ex) {
            Logger.getLogger(JSONLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
