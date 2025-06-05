package com.github.coffe.model.servicos;


public class Produto {
    private static int proxId = 1;
    private final int idProduto;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int idProduto, String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.idProduto = idProduto;
        if (idProduto >= proxId){
            proxId = idProduto + 1;
        }
    }

    public Produto(String nome, double preco, int estoque){
        this.idProduto = proxId++;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void exibirDados(){
        System.out.println("============ Dados do produto " + getIdProduto() + ": =============");
        System.out.println("Nome: " + getNome());
        System.out.println("Valor: " + getPreco());
        System.out.println("Estoque: " + getEstoque());
    }

    public String toString() {
        return getIdProduto() + ", " + getNome() + ", "  + getPreco() + ", " + getEstoque();
    }

    public static Produto fromString(String linha) {
        String[] dados = linha.split(", ");
        int id = Integer.parseInt(dados[0]);
        String nome = dados[1].trim();
        double preco = Double.parseDouble(dados[2].trim());
        int estoque = Integer.parseInt(dados[3].trim());

        return new Produto(id, nome, preco, estoque);
    }
}

