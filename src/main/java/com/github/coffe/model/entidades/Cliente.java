package com.github.coffe.model.entidades;

public class Cliente {
    private final int id_Cliente;
    private static int proxId = 1;
    private final String cpf;
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

    public Cliente(int id_Cliente, String cpf, String email, String nome, String senha) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.id_Cliente = id_Cliente;
        if (id_Cliente >= proxId) {
            proxId = id_Cliente + 1;
        }
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

    @Override
    public String toString() {
        return getId_Cliente() + ", " + getCpf() + ", " + getEmail() + ", " + getNome() + ", " + getSenha();
    }

    public static Cliente fromString(String linha){
        String[] dados = linha.split(", ");
        int id = Integer.parseInt(dados[0]);
        return new Cliente(id, dados[1], dados[2], dados[3], dados[4]);
    }
}
