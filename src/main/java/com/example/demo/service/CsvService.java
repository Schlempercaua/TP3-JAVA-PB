package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import java.io.File;
import com.example.demo.model.ClienteModel;
import com.example.demo.util.CsvUtil;


@Service
public class CsvService {

    private static final String OUTPUT_PATH = "clientes_processados.csv";

    public void processar(MultipartFile file) {
        try {
            List<ClienteModel> clientes = CsvUtil.lerClientes(file.getInputStream());

            // Transformação sem stream: nomes em maiúsculas
            List<ClienteModel> novosClientes = new ArrayList<>();
            for (ClienteModel c : clientes) {
                ClienteModel novo = new ClienteModel(
                    c.getNome().toUpperCase(),
                    c.getEmail(),
                    c.getCpf()
                );
                novosClientes.add(novo);
            }

            CsvUtil.escreverClientes(novosClientes, OUTPUT_PATH);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar CSV", e);
        }
    }

    public Resource getCsvProcessado() {
        return new FileSystemResource(new File(OUTPUT_PATH));
    }
}