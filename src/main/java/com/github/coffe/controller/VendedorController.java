package com.github.coffe.controller;

import com.github.coffe.model.servicos.ItemPedido;
import com.github.coffe.model.servicos.Pedido;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class VendedorController {
    private static final Logger log = LogManager.getLogger(VendedorController.class);
    private final PedidoController pedidoController;
    private final ProdutoController produtoController;

    public VendedorController() {
        pedidoController = new PedidoController();
        produtoController = new ProdutoController();
    }

    public boolean processarPedido(int id) {
        Pedido pedido = null;
        for (Pedido p : pedidoController.getPendentes()) {
            if (p.getId_Pedido() == id) {
                pedido = p;
            }
        }

        for (ItemPedido i : Objects.requireNonNull(pedido).getItens()) {
            if (!produtoController.verificarEstoque(i.getIdProduto(), i.getQuantidade())) {
                return false;
            }
        }

        for (ItemPedido i : pedido.getItens()) {
            produtoController.alterarEstoque(i.getIdProduto(), i.getQuantidade());
            pedido.setStatus("Processado");
            return true;
        }
        return false;
    }
}

