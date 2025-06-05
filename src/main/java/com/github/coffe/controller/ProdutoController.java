package com.github.coffe.controller;

import com.github.coffe.model.servicos.Produto;
import com.github.coffe.persistencia.ProdutoPersistencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Objects;


public class ProdutoController {
    private static final Logger log = LogManager.getLogger(ProdutoController.class);
    private final ArrayList<Produto> produtos;
    private final ProdutoPersistencia produtoPersistencia;

    public ProdutoController() {
        produtoPersistencia = new ProdutoPersistencia("src/main/java/com/github/coffe/dados/produtos.txt");
        produtos = produtoPersistencia.carregarDoArquivo();
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public boolean addProduto(String nome, double valor, int estoque){
        Produto p = new Produto(nome, valor, estoque);
        produtos.add(p);
        produtoPersistencia.salvarEmArquivo(produtos);

        return produtos.contains(p);
    }

    public boolean removerProduto(int id){
        Produto produto = null;
        for(Produto p: produtos){
            if(p.getIdProduto() == id){
                produtos.remove(p);
                produtoPersistencia.salvarEmArquivo(produtos);
                produto = p;
            }
        }
        return produtos.contains(produto);
    }

    public boolean alterarProduto(int id, int op, String campo) {
        Produto produto = null;
        for (Produto p : produtos) {
            if (p.getIdProduto() == id) {
                log.debug("Produto encontrado");
                produto = p;
            }
        }
        switch (op) {
            case 1:
                Objects.requireNonNull(produto).setNome(campo);
                log.debug("Nome alterado");

                break;

            case 2:
                Objects.requireNonNull(produto).setPreco(Double.parseDouble(campo));
                log.debug("Preço alterado");
                break;

            case 3:
                Objects.requireNonNull(produto).setEstoque(Integer.parseInt(campo));
                log.debug("estoque alterado");
                break;

            default:
                log.debug("Default");
                break;
        }
        produtoPersistencia.salvarEmArquivo(produtos);

        return produtos.contains(produto);
    }

}
