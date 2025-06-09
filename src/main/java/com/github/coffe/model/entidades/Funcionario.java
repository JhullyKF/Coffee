package com.github.coffe.model.entidades;

import com.github.coffe.interfaces.Identificavel;

public abstract class Funcionario implements Identificavel {
    private final int idFuncionario;
    private static int proxId = 1;
    private String cargo;
    private String nome;
    private String email;
    private String cpf;
    private double salarioFixo;
    private double salarioFinal;
    private String senha;

    //from string
    public Funcionario(int idFuncionario, String cargo, String nome, String email, String cpf, double salarioFixo, double salarioFinal, String senha) {
        this.cargo = cargo;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.salarioFixo = salarioFixo;
        this.salarioFinal = salarioFinal;
        this.senha = senha;
        this.idFuncionario = idFuncionario;
        if (idFuncionario >= proxId){
            proxId = idFuncionario + 1;
        }
    }

    //criar funcionario
    public Funcionario(String cargo, String nome, String email, String cpf, double salarioFixo, String senha){
        this.idFuncionario = proxId++;
        this.cargo = cargo;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.salarioFixo = salarioFixo;
        this.senha = senha;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSalario(){
        return salarioFixo;
    }

    public void setSalarioFinal(double salarioFinal){
        this.salarioFinal = salarioFinal;
    }
    public void setSalario(double salarioFixo){
        if (salarioFixo < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo.");
        }
        this.salarioFixo = salarioFixo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public abstract void exibirDados();

    public abstract void calcularSalarioFinal();

    public abstract String toString();
}
