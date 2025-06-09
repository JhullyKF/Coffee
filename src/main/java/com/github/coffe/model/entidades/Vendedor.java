package com.github.coffe.model.entidades;

public class Vendedor extends Funcionario{
    private int totalVendas;

    public Vendedor(String nome, String email,String cpf){
        super("Vendedor", nome, email, cpf, 2500.00, "Mudar@123");
    }
    //from string
    public Vendedor(int id, String cargo, String nome, String email, String cpf, double salarioFixo, double salarioFinal, String senha, int totalVendas){
        super(id, cargo, nome, email, cpf, salarioFixo, salarioFinal, senha);
        this.totalVendas = totalVendas;
    }

    public int getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(int totalVendas) {
        this.totalVendas = totalVendas;
    }

    @Override
    public void exibirDados() {
        System.out.println("=========== Dados do vendedor " + getIdFuncionario() + ": ==========");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("CPF: " + getCpf());
        System.out.println("Salário: " + getSalarioFixo());
    }

    @Override
    public void calcularSalarioFinal() {
        double bonus = totalVendas * 0.10;
        setSalarioFinal(getSalarioFixo() + bonus);
    }

    @Override
    public String toString() {
        return getIdFuncionario() + ", " + getCargo() + ", "  + getNome() + ", " + getEmail() + ", " + getCpf() + ", " +
                + getSalarioFixo() + ", "  + getSalarioFinal() + ", " + getSenha() + ", " + getTotalVendas();
    }

    public static Funcionario fromString(String linha) {
        String[] dados = linha.split(", ");
        int id = Integer.parseInt(dados[0]);
        String cargo = dados[1];
        String nome = dados[2];
        String email = dados[3];
        String cpf = dados[4];
        double salarioFixo = Double.parseDouble(dados[5]);
        double salarioFinal = Double.parseDouble(dados[6]);
        String senha = dados[7];
        int totalVendas = Integer.parseInt(dados[8]);

        return new Vendedor(id, cargo, nome, email, cpf, salarioFixo, salarioFinal, senha, totalVendas);
    }
}
