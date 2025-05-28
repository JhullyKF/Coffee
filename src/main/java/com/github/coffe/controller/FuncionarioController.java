package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.FuncionarioFactory;
import com.github.coffe.view.FuncionarioView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private static final Logger log = LogManager.getLogger(FuncionarioController.class);
    List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    File funcionariosFile = new File("dados/funcionarios.txt");

    public FuncionarioController(){
        carregarFuncionarios();
    }

    private void carregarFuncionarios(){
        if(!funcionariosFile.exists()) return;

        try(BufferedReader br = new BufferedReader(new FileReader(funcionariosFile))){
            String linha;
            while ((linha = br.readLine()) != null){
                funcionarios.add(FuncionarioFactory.fromString(linha));
            }
        } catch (IOException e){
            log.warn("Erro ao carregar funcionários: " + e.getMessage());
        }
    }

    public int verificaLogin(String cpf, String senha){
        for(Funcionario f: funcionarios){
            if(f.getCpf().equalsIgnoreCase(cpf) && f.getSenha().equals(senha)){
                log.info("Login de <" + f.getNome() + "> realizado com sucesso.");
                if (f.getCargo().equals("Gerente")){
                    return 1;
                } else if (f.getCargo().equals("Vendedor")) {
                    return 2;
                }
            }
        }
        log.warn("Tentativa de login mal sucedida para o usuário " + cpf + ", senha: " + senha);
        return 0;
    }
}
