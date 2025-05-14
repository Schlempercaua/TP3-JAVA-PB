package com.example.demo;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Lógica da CLI após o Spring Boot inicializar
        Scanner scanner = new Scanner(System.in);
        CsvClient client = new CsvClient();

        System.out.println("=== CLI CSV ===");
        System.out.print("Digite o caminho do arquivo CSV para enviar: ");
        String caminho = scanner.nextLine();

        boolean enviado = client.enviarArquivo(caminho);
        if (enviado) {
            System.out.println("Arquivo enviado com sucesso. Baixando o resultado...");
            client.baixarArquivo("resultado.csv");
        } else {
            System.out.println("Erro ao enviar arquivo.");
        }
    }
}