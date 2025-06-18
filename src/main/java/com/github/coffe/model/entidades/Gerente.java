package com.github.coffe.model.entidades;

public class Gerente extends Funcionario{
    private int totalVendas;

    public Gerente(String nome, String email, String cpf) {
        super("Gerente", nome, email, cpf, 5000.00,"Mudar@123");
    }

    //from string
    public Gerente(int id, String cargo, String nome, String email, String cpf, double salarioFixo, double salarioFinal, String senha){
        super(id, cargo, nome, email, cpf, salarioFixo, salarioFinal, senha);
    }

    public int getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(int totalVendas) {
        this.totalVendas = totalVendas;
    }

    @Override
    public void exibirDados() {
        System.out.println("============ Dados do " + getCargo() + ": =============");
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("CPF: " + getCpf());
        System.out.println("Salário: " + getSalarioFixo());
    }

    @Override
    public void calcularSalarioFinal() {
        double bonus = totalVendas * 0.05;
        setSalarioFinal(getSalarioFixo() + bonus);
    }

    @Override
    public String toString() {
        return getIdFuncionario() + ", " + getCargo() + ", " + getNome() + ", " + getEmail() + ", " + getCpf() + ", " + getSalarioFixo() + ", " + getSalarioFinal() + ", " + getSenha();
    }

    public static Funcionario fromString(String linha) {
        String[] dados = linha.split(", ");
        int idFuncionario = Integer.parseInt(dados[0]);
        String cargo = dados[1];
        String nome = dados[2];
        String email = dados[3];
        String cpf = dados[4];
        double salarioFixo = Double.parseDouble(dados[5]);
        double salarioFinal = Double.parseDouble(dados[6]);
        String senha = dados[7];

        return new Gerente(idFuncionario, cargo, nome, email, cpf, salarioFixo, salarioFinal, senha);
    }
}
