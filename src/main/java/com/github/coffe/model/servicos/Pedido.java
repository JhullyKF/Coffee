package com.github.coffe.model.servicos;

import com.github.coffe.controller.PedidoController;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemPedido> itens;
    private static int proxId = 1;
    private final int id_Pedido;
    private int idCliente;
    private int idVendedor;
    private String status;

    public Pedido(int id_Pedido, int idCliente, int idVendedor, String status,List<ItemPedido> itens){
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.itens = itens;
        this.status = status;
        this.id_Pedido = id_Pedido;
        if (id_Pedido >= proxId){
            proxId = id_Pedido + 1;
        }
    }

    public Pedido(int idCliente, int idVendedor, String status,List<ItemPedido> itens){
        this.id_Pedido = proxId++;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.itens = itens;
        this.status = status;
    }

    public int getId_Pedido(){
        return id_Pedido;
    }

    public int getIdCliente(){
        return idCliente;
    }

    public int getIdVendedor(){
        return idVendedor;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void exibirDados(){
        System.out.println("============ Dados do pedido " + getId_Pedido() + ": =============");
        System.out.println("ID do atendente: " + getIdVendedor());
        System.out.println("ID do cliente: " + getIdCliente());
        System.out.println("Status: " + getStatus());
        System.out.println("Itens: " + getItens());
    }

    public List<ItemPedido> getItens(){
        return itens;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId_Pedido()).append(", ").append(getIdCliente()).append(", ")
                .append(getIdVendedor()).append(", ").append(getStatus()).append(", ");

        for (int i = 0; i < itens.size(); i++) {
            sb.append(itens.get(i).toString());
            if (i < itens.size() - 1) sb.append("; ");
        }
        return sb.toString();
    }

    public static Pedido fromString(String linha) {
        String[] dados = linha.split(", ");
        int idPedido = Integer.parseInt(dados[0]);
        int idCliente = Integer.parseInt(dados[1].trim());
        int idVendedor = Integer.parseInt(dados[2].trim());
        String status = dados[3].trim();

        List<ItemPedido> itens = new ArrayList<>();
        String[] itensStr = dados[4].split("; ");
        for (String itemStr : itensStr) {
            itens.add(ItemPedido.fromString(itemStr));
        }

        return new Pedido(idPedido, idCliente, idVendedor, status, itens);
    }
}
