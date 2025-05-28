package com.github.coffe.model.entidades;

public class Vendedor extends Funcionario{
    private int idVendedor;
    public Vendedor(int id, String nome, String email,String cpf){
        super("Vendedor", nome, email, cpf, 2500.00, "Mudar@123");
        idVendedor = id;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor){
        this.idVendedor = idVendedor;
    }

    @Override
    public void exibirDados() {
        System.out.println("=========== Dados do vendedor " + getIdVendedor() + ": ==========");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("CPF: " + getEmail());
        System.out.println("Salário: " + getSalario());
    }

    @Override
    public double calcularSalario() {
        //double bonus = TotalVendasFuncionario * 0.10;
        //return (getSalario() + bonus);
        return 1;
    }
}
