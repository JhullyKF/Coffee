package com.github.coffe.model.entidades;

import com.github.coffe.interfaces.Identificavel;
import com.github.coffe.utils.ProximoIdManager;

public class Cliente implements Identificavel {
    private final int id_Cliente;
    private final String cpf;
    private String email;
    private String nome;
    private String senha;

    public Cliente(String cpf, String email, String nome, String senha){
        this.id_Cliente = ProximoIdManager.getProximoId(ProximoIdManager.cliente);
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void exibirDados() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Email: " + getEmail());
        System.out.println("Senha: " + getSenha());
    }

    public void exibirDadosGerencia() {
        System.out.println("============ Dados do cliente " + getId_Cliente() + ": =============");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Email: " + getEmail());
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
