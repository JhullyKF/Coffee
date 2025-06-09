package com.github.coffe.view;

import com.github.coffe.controller.PedidoController;
import com.github.coffe.model.servicos.ItemPedido;
import com.github.coffe.model.servicos.Pedido;

import java.util.ArrayList;

public class PedidoView {
    private final PedidoController pedidoController;

    public PedidoView() {
        this.pedidoController = new PedidoController();
    }

    public PedidoView(PedidoController pedidoController){
        this.pedidoController =  pedidoController;
    }

    public void mostrarPedidosValidados(){
        ArrayList<Pedido> validados = pedidoController.getPedidosValidados();
        if(validados.isEmpty()) {
            System.out.println("\nNenhum pedido já validado encontrado!");
            return;
        }

        for(Pedido pedidoValidado : validados){
            pedidoValidado.exibirDados();
        }
    }

    public void mostrarPedidosPendentes(){
        ArrayList<Pedido> pendentes = pedidoController.getPedidosPendentes();
        if(pendentes.isEmpty()) {
            System.out.println("\nNenhum pedido pendente encontrado!");
            return;
        }

        for(Pedido pedidoPendente : pendentes){
            pedidoPendente.exibirDados();
        }
    }

    public void mostrarTodosPedidos(){
        ArrayList<Pedido> pedidos = pedidoController.getPedidos();
        if(pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido encontrado!");
            return;
        }

        for(Pedido pedido : pedidos){
            pedido.exibirDados();
        }
    }

    public void mostrarPedidosCliente(){
        ArrayList<Pedido> pedidosDoUsuario = pedidoController.getPedidosUsuario();
        if(pedidosDoUsuario.isEmpty()) {
            System.err.println("\nNenhum pedido encontrado!");
            return;
        }

        for(Pedido pedidoDoCliente : pedidosDoUsuario){
            pedidoDoCliente.exibirDados();
        }
    }

    public void exibirCarrinho(){
        ArrayList<ItemPedido> carrinho = pedidoController.getCarrinho();
        if (carrinho.isEmpty()) {
            System.err.println("Carrinho está vazio.");
            return;
        }

        System.out.println("\n============ Seu carrinho =============");

        double total = 0;
        for (ItemPedido item : carrinho){
            System.out.println("ID: " + item.getIdProduto() + " | Nome: " + item.getNomeItem() +
                    " | Quantidade: " + item.getQuantidade() + " | Preço: " + item.getPreco());
            total += item.getPreco() * item.getQuantidade();
        }

        System.out.println("Total: " + total);
    }
}