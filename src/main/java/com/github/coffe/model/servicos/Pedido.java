package com.github.coffe.model.servicos;

import com.github.coffe.utils.ProximoIdManager;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemPedido> itens;
    private final int id_Pedido;
    private int idCliente;
    private String status;

    public Pedido(int id_Pedido, int idCliente, String status, List<ItemPedido> itens){
        this.idCliente = idCliente;
        this.itens = itens;
        this.status = status;
        this.id_Pedido = id_Pedido;
    }

    public Pedido(int idCliente, List<ItemPedido> itens){
        this.id_Pedido = ProximoIdManager.getProximoId(ProximoIdManager.pedido);
        this.idCliente = idCliente;
        this.itens = itens;
        this.status = "Pendente";
    }

    public int getId_Pedido(){
        return id_Pedido;
    }

    public int getIdCliente(){
        return idCliente;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void exibirDados(){
        System.out.println("============ Dados do pedido " + getId_Pedido() + ": =============");
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
                .append(getStatus()).append(", ");

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
        String status = dados[2].trim();

        List<ItemPedido> itens = new ArrayList<>();
        String[] itensStr = dados[3].split("; ");
        for (String itemStr : itensStr) {
            itens.add(ItemPedido.fromString(itemStr));
        }

        return new Pedido(idPedido, idCliente, status, itens);
    }
}
