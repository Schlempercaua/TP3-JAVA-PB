package com.example.demo.model;

import lombok.Data;

@Data
public class ClienteModel {
    
    private String nome;

    private String cpf;


    private String email;

    public ClienteModel() {
    }

    public ClienteModel(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }
}
