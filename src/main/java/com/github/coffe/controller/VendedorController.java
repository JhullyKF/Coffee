package com.github.coffe.controller;

import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.model.servicos.ItemPedido;
import com.github.coffe.model.servicos.Pedido;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class VendedorController {
    private static final Logger log = LogManager.getLogger(VendedorController.class);
    private final PedidoController pedidoController;
    private final ProdutoController produtoController;
    private Vendedor vendedor;
    private final ArrayList<Pedido> totalVendas = new ArrayList<>();


    public VendedorController() {
        pedidoController = new PedidoController();
        produtoController = new ProdutoController();
    }

    public ArrayList<Pedido> getTotalVendas(){
        try {
            if (vendedor == null){
                log.warn("Vendedor não carregado");
                throw new IllegalArgumentException("vendedor nulo.");
            } else {
                for (Pedido p : pedidoController.getPedidos()) {
                    if (p.getIdVendedor() == vendedor.getIdFuncionario()) {
                        totalVendas.add(p);
                    }
                }
                log.info("Total de vendas do vendedor {} carregadas com sucesso", vendedor.getTotalVendas());
                return totalVendas;
            }
        } catch (Exception e){
            log.warn("Erro ao carregar total de vendas do vendedor {}: {}",
                    (vendedor != null ? vendedor.getIdFuncionario() : "desconhecido"), e.getMessage());}
        return null;
    }

    public void loginVendedor(Vendedor vendedor){
        this.vendedor = vendedor;
        this.vendedor.setTotalVendas(getTotalVendas().size());
    }

    public boolean processarPedido(int id) {
        Pedido pedido = null;
        for (Pedido p : pedidoController.getPendentes()) {
            if (p.getId_Pedido() == id) {
                pedido = p;
                break;
            }
        }

        try {
            if (pedido == null || vendedor == null){
                throw new IllegalArgumentException("Dados nulos.");
            }

            for (ItemPedido i : pedido.getItens()) {
                if (!produtoController.verificarEstoque(i.getIdProduto(), i.getQuantidade())) {
                    throw new IllegalArgumentException("Produto sem estoque suficiente: " + i.getIdProduto());
                }
            }

            for (ItemPedido i : pedido.getItens()) {
                produtoController.alterarEstoque(i.getIdProduto(), i.getQuantidade());
                pedido.setStatus("Processado");
                pedido.setIdVendedor(vendedor.getIdFuncionario());
            }
            return true;
        } catch (Exception e){
            log.warn("Erro ao processar pedido: ", e);
        }
        return false;
    }
}

