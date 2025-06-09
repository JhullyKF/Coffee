package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.persistencia.FuncionarioPersistencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class FuncionarioController {
    private static final Logger log = LogManager.getLogger(FuncionarioController.class);
    private final ArrayList<Funcionario> funcionarios;
    private final FuncionarioPersistencia funcionarioPersistencia;

    public FuncionarioController(){
        funcionarioPersistencia = new FuncionarioPersistencia("src/main/java/com/github/coffe/dados/funcionarios.txt");
        funcionarios = funcionarioPersistencia.carregarDoArquivo();
    }

    public FuncionarioPersistencia getFuncionarioPersistencia() {
        return funcionarioPersistencia;
    }

    public ArrayList<Funcionario> getFuncionarios(){
        return funcionarios;
    }

    public Funcionario verificaLogin(String cpf, String senha){
        for(Funcionario f: funcionarios){
            if(f.getCpf().equals(cpf) && f.getSenha().equals(senha.trim())){
                log.info("Login de <{}> realizado com sucesso.", f.getNome());
                return f;
            }
        }
        log.warn("Tentativa de login mal sucedida para o usuário {}, senha: {}", cpf, senha);
        return null;
    }
}
