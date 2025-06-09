package com.github.coffe.controller;

import com.github.coffe.model.entidades.Gerente;
import com.github.coffe.model.servicos.ItemPedido;
import com.github.coffe.model.servicos.Pedido;
import com.github.coffe.model.servicos.Produto;
import com.github.coffe.persistencia.PedidoPersistencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class PedidoController {
    private static final Logger log = LogManager.getLogger(PedidoController.class);
    private final ArrayList<Pedido> pedidos;
    private final ArrayList<Pedido> pendentes = new ArrayList<>();
    private final ArrayList<Pedido> validados = new ArrayList<>();
    private final PedidoPersistencia pedidoPersistencia;
    private final ArrayList<ItemPedido> carrinho = new ArrayList<>();
    private final ProdutoController produtoController = new ProdutoController();
    private ClienteController clienteController;

    public PedidoController(){
        pedidoPersistencia = new PedidoPersistencia("src/main/java/com/github/coffe/dados/pedidos.txt");
        pedidos = pedidoPersistencia.carregarDoArquivo();
        carregarStatusPedidos();
    }

    public PedidoController(ClienteController clienteController){
        pedidoPersistencia = new PedidoPersistencia("src/main/java/com/github/coffe/dados/pedidos.txt");
        pedidos = pedidoPersistencia.carregarDoArquivo();
        this.clienteController = clienteController;
        carregarStatusPedidos();
    }

    //Adicionar, remover e listar itens do carrinho

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
        verificarProduto(itemPedido);
        carrinho.add(itemPedido);
        return true;
    }

    public ArrayList<ItemPedido> getCarrinho(){
        return carrinho;
    }

    public boolean removerItem(int id, int qtd){
        Iterator<ItemPedido> iterator = carrinho.iterator();
        while (iterator.hasNext()) {
            ItemPedido item = iterator.next();
            if (item.getIdProduto() == id) {
                int qtdReduzida = item.getQuantidade() - qtd;
                if (qtdReduzida == 0) {
                    iterator.remove();
                } else if(qtdReduzida < 0){
                    return false;
                } else{
                    item.setQuantidade(qtdReduzida);
                }
                return true;
            }
        }
        return false;
    }

    public void verificarProduto(ItemPedido item){
        for(Produto produto : produtoController.getProdutos()){
            if(item.getIdProduto() == produto.getIdProduto()){
                item.setNomeItem(produto.getNome());
                item.setPreco(produto.getPreco());
            }
        }
    }

    public boolean carrinhoVazio(){
        return carrinho.isEmpty();
    }

    //Criando objeto Pedido e salvando em arquivo
    public boolean finalizarPedido(){
        if(!carrinhoVazio()){
            Pedido pedido = new Pedido(clienteController.getUsuario().getId_Cliente(), new ArrayList<>(carrinho));
            pedidos.add(pedido);
            pedidoPersistencia.salvarEmArquivo(pedidos);
            carrinho.clear();
            return true;
        }
        return false;
    }

    //Listagem dos pedidos feitos
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Pedido> getPendentes(){
        return pendentes;
    }

    public ArrayList<Pedido> getPedidosUsuario(){
        ArrayList<Pedido> pedidosUsuario = new ArrayList<>();
        for(Pedido pedido : pedidos){
            if(pedido.getIdCliente() == clienteController.getUsuario().getId_Cliente()){
                pedidosUsuario.add(pedido);
            }
        }
        return pedidosUsuario;
    }

    public ArrayList<Pedido> getPedidosPendentes(){
        return pendentes;
    }

    public ArrayList<Pedido> getPedidosValidados(){
        return validados;
    }

    public void carregarStatusPedidos(){
        for (Pedido p: pedidos){
            if(p.getStatus().equals("Pendente")){
                pendentes.add(p);
            } else{
                validados.add(p);
            }
        }
    }

}
