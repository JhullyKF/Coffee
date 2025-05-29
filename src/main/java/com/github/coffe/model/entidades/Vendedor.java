package com.github.coffe.model.entidades;

public class Vendedor extends Funcionario{

    public Vendedor(int id, String nome, String email,String cpf){
        super(id, "Vendedor", nome, email, cpf, 2500.00, "Mudar@123");
    }

    public Vendedor(int id, String cargo, String nome, String email, String cpf, double salario, String senha){
        super(id, cargo, nome, email, cpf, salario, senha);
    }

    @Override
    public void exibirDados() {
        System.out.println("=========== Dados do vendedor " + getIdFuncionario() + ": ==========");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("CPF: " + getCpf());
        System.out.println("Salário: " + getSalario());
    }

    @Override
    public double calcularSalario() {
        //double bonus = TotalVendasFuncionario * 0.10;
        //return (getSalario() + bonus);
        return 1;
    }

    @Override
    public String toString() {
        return getIdFuncionario() + ", " + getCargo() + ", "  + getNome() + ", " + getEmail() + ", " + getCpf() + ", " +
                getSalario() + ", " + getSenha();
    }

    public static Vendedor fromString(String linha) {
        String[] dados = linha.split(", ");
        int id = Integer.parseInt(dados[0]);
        String cargo = dados[1].trim();
        String nome = dados[2].trim();
        String email = dados[3].trim();
        String cpf = dados[4].trim();
        double salario = Double.parseDouble(dados[5].trim());
        String senha = dados[6];

        return new Vendedor(id, cargo, nome, email, cpf, salario, senha);
    }
}
