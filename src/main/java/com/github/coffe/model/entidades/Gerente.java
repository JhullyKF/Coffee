package com.github.coffe.model.entidades;

import java.util.Locale;

public class Gerente extends Funcionario{
    public Gerente(String nome, String email, String cpf) {
        super(0,"Gerente", nome, email, cpf, 5000.00,"Mudar@123");
    }
    public Gerente(int id, String cargo, String nome, String email, String cpf, double salario, String senha){
        super(id, cargo, nome, email, cpf, salario, senha);
    }

    @Override
    public void exibirDados() {
        System.out.println("============ Dados do " + getCargo() + ": =============");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("CPF: " + getCpf());
        System.out.println("Salário: " + getSalario());
    }

    @Override
    public double calcularSalario() {
        //double bonus = totalVendas * 0.15
        return (getSalario() /*+ bonus*/);
    }

    @Override
    public String toString() {
        return getIdFuncionario() + ", " + getCargo() + ", " + getNome() + ", " + getEmail() + ", " + getCpf() + ", " + getSalario() + ", " + getSenha();
    }

    public static Gerente fromString(String linha) {
        String[] dados = linha.split(", ");
        int idFuncionario = Integer.parseInt(dados[0].trim());
        String cargo = dados[1].trim();
        String nome = dados[2].trim();
        String email = dados[3].trim();
        String cpf = dados[4].trim();
        double salario = Double.parseDouble(dados[5].trim());
        String senha = dados[6].trim();

        return new Gerente(idFuncionario, cargo, nome, email, cpf, salario, senha);
    }
}
