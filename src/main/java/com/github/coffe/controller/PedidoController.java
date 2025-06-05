package com.github.coffe.controller;

import com.github.coffe.model.servicos.ItemPedido;
import com.github.coffe.model.servicos.Pedido;
import com.github.coffe.model.servicos.Produto;
import com.github.coffe.persistencia.PedidoPersistencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PedidoController {
    private static final Logger log = LogManager.getLogger(PedidoController.class);
    private final ArrayList<Pedido> pedidos;
    private ArrayList<Pedido> pendentes;
    private final PedidoPersistencia produtoPersistencia;
    private ArrayList<Pedido> pendentes = new ArrayList<>();
    private final PedidoPersistencia pedidoPersistencia;
    private final ArrayList<ItemPedido> carrinho = new ArrayList<>();
    private final ProdutoController produtoController = new ProdutoController();

    public PedidoController(){
        pedidoPersistencia = new PedidoPersistencia("src/main/java/com/github/coffe/dados/pedidos.txt");
        pedidos = pedidoPersistencia.carregarDoArquivo();
        for (Pedido p: pedidos){
            if(p.getStatus().equals("Pendente")){
                pendentes.add(p);
            }
        }
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Pedido> getPendentes(){
        return pendentes;
    }

    public boolean addItem(int id, int qtd){
        Produto produto = produtoController.getProdutoPorId(id);
        if(produto == null || qtd <= 0){
            return false;
        }

        for(ItemPedido itemPedido : carrinho){
            if(itemPedido.getIdProduto() == id){
                int novaQtd = itemPedido.getQuantidade() + qtd;
                if(novaQtd > produto.getEstoque()){
                    return false;
                }
                itemPedido.setQuantidade(novaQtd);
                return true;
            }
        }

        if(qtd > produto.getEstoque()){
            return false;
        }

        ItemPedido itemPedido = new ItemPedido(id, qtd);
        carrinho.add(itemPedido);
        return true;
    }
}
