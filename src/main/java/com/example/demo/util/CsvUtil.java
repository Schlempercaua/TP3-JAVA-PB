package com.example.demo.util;


import com.example.demo.model.ClienteModel;
import com.opencsv.*;
import com.opencsv.bean.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvUtil {

    public static List<ClienteModel> lerClientes(InputStream inputStream) throws IOException {
        try (Reader reader = new InputStreamReader(inputStream)) {
            CsvToBean<ClienteModel> csvToBean = new CsvToBeanBuilder<ClienteModel>(reader)
                    .withType(ClienteModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<ClienteModel> clientes = csvToBean.parse();
    
            // Verifique os dados lidos
            for (ClienteModel cliente : clientes) {
                System.out.println(cliente.getNome() + " | " + cliente.getCpf() + " | " + cliente.getEmail());
            }
    
            return clientes;
        }
    }
    

    public static void escreverClientes(List<ClienteModel> clientes, String caminho) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(caminho))) {
            StatefulBeanToCsv<ClienteModel> beanToCsv = new StatefulBeanToCsvBuilder<ClienteModel>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(clientes);
        } catch (Exception e) {
            throw new IOException("Erro ao escrever CSV", e);
        }
    }
}