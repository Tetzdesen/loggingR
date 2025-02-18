package com.br.adapter;

import java.io.StringWriter;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class OpenCSVAdapter implements ICSVSerialize {

    @Override
    public String serializarCSV(Object object) {
        StringWriter writer = new StringWriter();
        try {
            StatefulBeanToCsv<Object> beanToCsv = new StatefulBeanToCsvBuilder<>(writer).build();
            beanToCsv.write(object);
            return writer.toString();
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException("Não é possível serializar para CSV: " + e.getMessage(), e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException("Não é possível serializar para CSV: " + e.getMessage(), e);
        }
    }
}
