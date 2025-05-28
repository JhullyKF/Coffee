package com.github.coffe.model.entidades;

public class Gerente extends Funcionario{
    public Gerente(String nome, String email, String cpf) {
        super("Gerente", nome, email, cpf, 5000.00,"Mudar@123");
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
}
