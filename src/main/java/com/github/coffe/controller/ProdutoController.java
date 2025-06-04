package com.github.coffe.controller;

import com.github.coffe.model.servicos.Produto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class ProdutoController {
    private static final Logger log = LogManager.getLogger(ProdutoController.class);
    public ArrayList<Produto> produtos = new ArrayList<>();
    File produtosFile = new File("src/main/java/com/github/coffe/dados/produtos.txt");
    Set<Integer> idsUsados = new TreeSet<>();

    public ProdutoController() {
        produtos = getProdutos();
    }

    public ArrayList<Produto> getProdutos() {

        try (BufferedReader br = new BufferedReader(new FileReader(produtosFile))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                produtos.add(Produto.fromString(linha));
            }
            log.info("Produtos carregados com sucesso");
        } catch (IOException e) {
            log.warn("Erro ao carregar produtos: " + e.getMessage());
        }
        return produtos;
    }


    public int atribuiId() {
        int idLivre = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(produtosFile))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    String[] partes = linha.split(", ");
                    int id = Integer.parseInt(partes[0].trim());
                    idsUsados.add(id);
                }
            }
        } catch (IOException e) {
            log.error("Erro ao atribuir id ao produto " + e.getMessage());
        }
        while (idsUsados.contains(idLivre)) {
            idLivre++;
        }
        return idLivre;
    }

    public boolean addProduto(int id, String nome, double valor, int estoque){
        produtos.add(new Produto(id, nome, valor, estoque));
        Produto p = null;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(produtosFile))) {
            for (Produto produto : produtos) {
                bw.write(produto.toString());
                bw.newLine();
                p = produto;
                log.info("Produto " + produto.getNome() + " alterado com sucesso");
            }
        } catch (IOException e) {
            log.warn("Erro ao alterar produto " + e.getMessage());
        }

        if (produtos.contains(p)) {
            return true;
        }
        return false;
    }

    public boolean removerProduto(int id){
        for(Produto p: produtos){
            if(p.getIdProduto() == id){
                produtos.remove(p);
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(produtosFile))){
                    for(Produto produto : produtos){
                        bw.write(produto.toString());
                        bw.newLine();
                        log.info("Produto " + produto.getNome() + " cadastrado com sucesso");
                    }
                    return true;
                }   catch (IOException e) {
                    log.warn("Erro ao salvar produto " + e.getMessage());
                }
            }
        }
        return false;
    }

    public boolean alterarProduto(int id, int op, String campo) {
        for (Produto p : produtos) {
            if (p.getIdProduto() == id) {
                log.debug("Produto encontrado");

                switch (op) {
                    case 1:
                        p.setNome(campo);
                        log.debug("Nome alterado");
                        break;

                    case 2:
                        p.setPreco(Double.parseDouble(campo));
                        log.debug("Preço alterado");
                        break;

                    case 3:
                        p.setEstoque(Integer.parseInt(campo));
                        log.debug("estoque alterado");
                        break;

                    default:
                        log.debug("Default");
                        break;
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(produtosFile))) {
                    for (Produto produto : produtos) {
                        bw.write(produto.toString());
                        bw.newLine();
                        log.info("Produto " + produto.getNome() + " alterado com sucesso");
                    }
                } catch (IOException e) {
                    log.warn("Erro ao alterar produto " + e.getMessage());
                }

                if (produtos.contains(p)) {
                    return true;
                }
            }
        }
        return false;
    }

}
