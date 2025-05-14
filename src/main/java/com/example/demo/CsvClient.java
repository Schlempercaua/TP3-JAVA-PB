package com.example.demo;

import java.io.File;
import java.io.IOException;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;

public class CsvClient {

    public boolean enviarArquivo(String caminhoArquivo) {
        try {               
            // Criando a conexão com a API
            CloseableHttpClient client = HttpClients.createDefault();

            // Criando o método HTTP POST para envio de arquivos
            HttpPost post = new HttpPost("http://localhost:8080/api/csv/upload");

            File file = new File(caminhoArquivo);
            HttpEntity entity = MultipartEntityBuilder.create()
                    .addBinaryBody("file", file)
                    .build();

            post.setEntity(entity);

            // Executando a requisição
            try (CloseableHttpResponse response = client.execute(post)) {
                int statusCode = response.getCode();

                if (statusCode == 200) {
                    return true; // Arquivo enviado com sucesso
                } else {
                    System.out.println("Erro na requisição. Status: " + statusCode);
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void baixarArquivo(String caminho) {
        // Lógica para baixar o arquivo (se necessário, como mencionado no código)
        System.out.println("Arquivo baixado: " + caminho);
    }
}
