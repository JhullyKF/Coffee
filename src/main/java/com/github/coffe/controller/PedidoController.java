package com.github.coffe.controller;

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
    private ArrayList<Pedido> pedidos;
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

    public PedidoPersistencia getPedidoPersistencia() {
        return pedidoPersistencia;
    }

    //Adicionar, remover e listar itens do carrinho

    public boolean addItem(int id, int qtd){
        Produto produto = produtoController.getProdutoPorId(id);
        if(produto == null || qtd <= 0){
            log.warn("Tentativa de adicionar item inválido no carrinho. ProdutoID: {}, Quantidade: {}", id, qtd);
            return false;
        }

        for(ItemPedido itemPedido : carrinho){
            if(itemPedido.getIdProduto() == id){
                int novaQtd = itemPedido.getQuantidade() + qtd;
                itemPedido.setQuantidade(novaQtd);
                log.info("Item adicionado ao carrinho com sucesso. ProdutoID: {}, Quantidade: {}", id, qtd);
                return true;
            }
        }

        ItemPedido itemPedido = new ItemPedido(id, qtd);
        verificarProduto(itemPedido);
        carrinho.add(itemPedido);
        log.info("Item adicionado ao carrinho com sucesso. ProdutoID: {}, Quantidade: {}", id, qtd);
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
                    log.info("Item removido do carrinho. ProdutoID: {}", id);
                } else if(qtdReduzida < 0){
                    log.warn("Quantidade para remover item do carrinho maior que a existente. ProdutoID: {}, Atual: {}, Tentativa: {}", id, item.getQuantidade(), qtd);
                    return false;
                } else{
                    log.info("Quantidade do produto {} no carrinho reduzida para {}", item.getNomeItem(), qtdReduzida);
                    item.setQuantidade(qtdReduzida);
                }
                return true;
            }
        }
        log.warn("Produto a ser removido não encontrado. ProdutoID: {}", id);
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
            log.info("Pedido finalizado para cliente ID {}", pedido.getIdCliente());
            return true;
        }
        log.warn("Tentativa de finalizar pedido com carrinho vazio");
        return false;
    }

    //Listagem dos pedidos feitos
    public ArrayList<Pedido> getPedidos() {
        return new ArrayList<>(pedidos);
    }

    public void atualizarArquivo(){
        pedidos = pedidoPersistencia.carregarDoArquivo();
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
        pendentes.clear();
        validados.clear();
        atualizarArquivo();
        for (Pedido p: pedidos){
            if(p.getStatus().equals("Pendente")){
                pendentes.add(p);
            } else{
                validados.add(p);
            }
        }
    }

    public Pedido getPedidoId(int id){
        for(Pedido p: pedidos){
            if (id == p.getId_Pedido()){
                return p;
            }
        }
        return null;
    }
}
