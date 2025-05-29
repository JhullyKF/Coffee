package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.FuncionarioFactory;
import com.github.coffe.model.entidades.Gerente;
import com.github.coffe.model.entidades.Vendedor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class FuncionarioController {
    private static final Logger log = LogManager.getLogger(FuncionarioController.class);
    public ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    File funcionariosFile = new File("src/main/java/com/github/coffe/dados/funcionarios.txt");
    Set<Integer> idsUsados = new TreeSet<>();

    public FuncionarioController(){
        funcionarios = carregarFuncionarios();
    }

    public ArrayList<Funcionario> carregarFuncionarios(){
        funcionarios.clear();
        if (!funcionariosFile.exists()){
            File funcionariosFile = new File("src/main/java/com/github/coffe/dados/funcionarios.txt");
        }

        try(BufferedReader br = new BufferedReader(new FileReader(funcionariosFile))){
            String linha;
            while ((linha = br.readLine()) != null){
                funcionarios.add(FuncionarioFactory.fromString(linha));
            }
            log.info("Funcionarios carregados com sucesso");
        } catch (IOException e){
            log.warn("Erro ao carregar funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    public int verificaLogin(String cpf, String senha){
        carregarFuncionarios();
        for(Funcionario f: funcionarios){
            if(f.getCpf().trim().equals(cpf) && f.getSenha().trim().equals(senha.trim())){
                log.info("Login de <" + f.getNome() + "> realizado com sucesso.");
                if(f instanceof Gerente) return 1;
                if(f instanceof Vendedor) return 2;
                log.info("Login realizado pelo funcionario " + f.getNome());
            }
        }
        log.warn("Tentativa de login mal sucedida para o usuário " + cpf + ", senha: " + senha);
        return 0;
    }

    public int atribuiId(){
        int idLivre = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(funcionariosFile))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    String[] partes = linha.split(", ");
                    int id = Integer.parseInt(partes[0].trim());
                    idsUsados.add(id);
                }
            }
        } catch (IOException e) {
            log.error("Erro ao atribuir id ao funcionario " + e.getMessage());
        }
        while(idsUsados.contains(idLivre)){
            idLivre++;
        }
        return idLivre;
    }
}
