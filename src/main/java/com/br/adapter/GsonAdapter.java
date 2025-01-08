package com.br.adapter;

import com.google.gson.Gson;

/**
 *
 * @author tetzner
 */
public class GsonAdapter implements IJsonSerializer {
    
    private final Gson gson;

    public GsonAdapter() {
        this.gson = new Gson();
    }
    
    @Override
    public String serializar(Object object) {
        return gson.toJson(object);       
    }
    
}
