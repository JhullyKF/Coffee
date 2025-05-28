package com.github.coffe.model.servicos;

public class ItemPedido {
    private int id_ItemPedido;
    private int quantidade;

    public ItemPedido(int id_ItemPedido, int quantidade) {
        this.id_ItemPedido = id_ItemPedido;
        this.quantidade = quantidade;
    }

    public int getId_ItemPedido() {
        return id_ItemPedido;
    }

    public void setId_ItemPedido(int id_ItemPedido) {
        this.id_ItemPedido = id_ItemPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
