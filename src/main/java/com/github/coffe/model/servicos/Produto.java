package com.github.coffe.model.servicos;


public class Produto {
    private static int proxId = 1;
    private final int idProduto;
    private String nome;
    private double preco;
    private int estoque;
    private String descricao;

    public Produto(int idProduto, String nome, String descricao, double preco, int estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.idProduto = idProduto;
        if (idProduto >= proxId){
            proxId = idProduto + 1;
        }
    }

    public Produto(String nome, String descricao, double preco, int estoque){
        this.idProduto = proxId++;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        System.out.printf("%-2d | %-15s | %-85s | R$%-2.2f | %d\n",
                getIdProduto(), getNome(), getDescricao(), getPreco(), getEstoque());
    }

    public String toString() {
        return getIdProduto() + "; " + getNome() + "; " + getDescricao() + "; "  + getPreco() + "; " + getEstoque();
    }

    public static Produto fromString(String linha) {
        String[] dados = linha.split("; ");
        int id = Integer.parseInt(dados[0]);
        String nome = dados[1];
        String descricao = dados[2];
        double preco = Double.parseDouble(dados[3]);
        int estoque = Integer.parseInt(dados[4]);

        return new Produto(id, nome, descricao, preco, estoque);
    }
}

