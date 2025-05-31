package com.github.coffe.model.servicos;
import com.github.coffe.model.entidades.Vendedor;

public class Produto {
    private int idProduto;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int idProduto, String nome, double preco, int estoque) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
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

