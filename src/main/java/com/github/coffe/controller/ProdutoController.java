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

    public boolean verificarEstoque(int id, int quantidade){
        for(Produto p: produtos){
            if (p.getIdProduto() == id){
                if (quantidade <= p.getEstoque()){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean alterarEstoque(int id, int quantidade){
        try {
            for (Produto p : produtos) {
                if (p.getIdProduto() == id) {
                    if (quantidade <= p.getEstoque()) {
                        p.setEstoque(p.getEstoque() - quantidade);
                        log.info("Estoque atualizado com sucesso");
                    } else{
                        throw new IllegalArgumentException("Produto sem estoque suficiente: " + p.getIdProduto());

                    }
                }
            }
            produtoPersistencia.salvarEmArquivo(produtos);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean addProduto(String nome, String desc, double valor, int estoque){
        Produto p = new Produto(nome, desc, valor, estoque);
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
                break;
            }
        }

        if (produto == null){
            throw new IllegalArgumentException("Produto não encontrado");
        } else {
            switch (op) {
                case 1:
                    produto.setNome(campo);
                    log.info("Nome do produto {} alterado com sucesso", produto.getIdProduto());
                    break;

                case 2:
                    produto.setDescricao(campo);
                    log.info("Descrição do produto {} alterada com sucesso", produto.getIdProduto());
                    break;

                case 3:
                    produto.setPreco(Double.parseDouble(campo));
                    log.info("Preço do produto {} alterado com sucesso", produto.getIdProduto());
                    break;

                case 4:
                    produto.setEstoque(Integer.parseInt(campo));
                    log.info("Estoque do produto {} alterado com sucesso", produto.getIdProduto());
                    break;

                default:
                    log.debug("Default");
                    break;
            }
            produtoPersistencia.salvarEmArquivo(produtos);
        }

        return produtos.contains(produto);
    }

    public Produto getProdutoPorId(int id){
        for (Produto p : produtos){
            if (p.getIdProduto() == id){
                return p;
            }
        }
        return null;
    }


}
