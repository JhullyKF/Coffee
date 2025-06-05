package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Gerente;
import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.persistencia.FuncionarioPersistencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class FuncionarioController {
    private static final Logger log = LogManager.getLogger(FuncionarioController.class);
    private final ArrayList<Funcionario> funcionarios;
    private final FuncionarioPersistencia funcionarioPersistencia;

    public FuncionarioController(){
        this.funcionarioPersistencia = new FuncionarioPersistencia("src/main/java/com/github/coffe/dados/funcionarios.txt");
        funcionarios = funcionarioPersistencia.carregarDoArquivo();
    }

    public ArrayList<Funcionario> getFuncionarios(){
        return funcionarios;
    }

    public int verificaLogin(String cpf, String senha){
        for(Funcionario f: funcionarios){
            if(f.getCpf().trim().equals(cpf) && f.getSenha().trim().equals(senha.trim())){
                log.info("Login de <{}> realizado com sucesso.", f.getNome());
                if(f instanceof Gerente) return 1;
                if(f instanceof Vendedor) return 2;
                log.info("Login realizado pelo funcionario {}", f.getNome());
            }
        }
        log.warn("Tentativa de login mal sucedida para o usuário {}, senha: {}", cpf, senha);
        return 0;
    }
}
