package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.servicos.Produto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class ProdutoController {
    private static final Logger log = LogManager.getLogger(ProdutoController.class);
    ArrayList<Produto> produtos = new ArrayList<>();
    File produtosFile = new File("dados/produtos.txt");
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
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(produtosFile))){
            for(Produto p : produtos){
                bw.write(p.toString());
                bw.newLine();
                log.info("Funcionário " + p.getNome() + " cadastrado com sucesso");
                return true;
            }
        }   catch (IOException e) {
            log.warn("Erro ao salvar funcionario " + e.getMessage());
        }
        return false;
    }


}
