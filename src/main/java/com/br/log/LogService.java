package com.br.log;

/**
 *
 * @author tetzner
 */
public class LogService {
    
    private final ILog tipoLog;

    public LogService(ILog tipoLog) {
        this.tipoLog = tipoLog;
    }
  
    public void escreverMensagem(Object object){
        tipoLog.escrever(object);
    }

}
