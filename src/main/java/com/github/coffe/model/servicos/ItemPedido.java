package com.github.coffe.model.servicos;

public class ItemPedido {
    private int idProduto;
    private int quantidade;
    private String nomeItem;
    private Double preco;

    public ItemPedido(int idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setId_ItemPedido(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String toString() {
        return getIdProduto() + ": " + getQuantidade();
    }

    public static ItemPedido fromString(String texto) {
        String[] partes = texto.split(": ");
        int idProduto = Integer.parseInt(partes[0]);
        int quantidade = Integer.parseInt(partes[1]);
        return new ItemPedido(idProduto, quantidade);
    }
}
