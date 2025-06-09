package com.github.coffe.view;

import com.github.coffe.controller.ProdutoController;
import com.github.coffe.model.servicos.Produto;

public class ProdutoView {
    private ProdutoController pc = new ProdutoController();

    public boolean listarProdutos(){
        if(!pc.getProdutos().isEmpty()) {
            System.out.printf("%-2s | %-15s | %-85s | %-6s | %-3s\n",
                    "ID", "Nome", "Descrição", "Valor", "Estoque");
            System.out.println("---+-----------------+---------------------------------------------------------------------------------------+--------+---------");
            for (Produto p : pc.getProdutos()) {
                p.exibirDados();
            }
            return true;
        }
        return false;
    }
}