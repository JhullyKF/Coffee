package com.github.coffe.controller;

import com.github.coffe.model.servicos.Produto;
import com.github.coffe.persistencia.ProdutoPersistencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;


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

    public Produto alterarEstoque(int id, int quantidade){
        Produto produto = getProdutoPorId(id);
        if (produto == null){
            log.warn("Produto não encontrado");
            throw new IllegalArgumentException("Produto não encontrado");
        }

        if (produto.getEstoque() < quantidade){
            log.warn("Produto sem estoque suficiente: {}", id);
            return produto;

        }
        produto.setEstoque(produto.getEstoque() - quantidade);
        produtoPersistencia.salvarEmArquivo(produtos);
        log.info("Estoque do produto {} atualizado com sucesso", id);
        return null;
    }

    public boolean addProduto(String nome, String desc, double valor, int estoque){
        Produto p = new Produto(nome, desc, valor, estoque);
        produtos.add(p);
        produtoPersistencia.salvarEmArquivo(produtos);

        return produtos.contains(p);
    }

    public boolean removerProduto(int id){
        Produto produto = getProdutoPorId(id);
        if(produto == null){
            log.warn("Produto não encontrado");
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produtos.remove(produto);
        produtoPersistencia.salvarEmArquivo(produtos);
        return produtos.contains(produto);
    }

    public boolean alterarProduto(int id, int op, String campo) {
        Produto produto = getProdutoPorId(id);

        if (produto == null){
            log.warn("Produto não encontrado");
            throw new IllegalArgumentException("Produto não encontrado");
        }

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