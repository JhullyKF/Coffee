package com.github.coffe.model.entidades;

public class Cliente {
    private final int id_Cliente;
    private static int proxId = 1;
    private  String cpf;
    private String email;
    private String nome;
    private String senha;

    public Cliente(String cpf, String email, String nome, String senha){
        this.id_Cliente = proxId++;
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public String getSenha() {
        return senha;
    }
}
