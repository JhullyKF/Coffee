package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.persistencia.FuncionarioPersistencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class FuncionarioController {
    private static final Logger log = LogManager.getLogger(FuncionarioController.class);
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private final FuncionarioPersistencia funcionarioPersistencia;

    public FuncionarioController(){
        funcionarioPersistencia = new FuncionarioPersistencia("src/main/java/com/github/coffe/dados/funcionarios.txt");
        funcionarios = funcionarioPersistencia.carregarDoArquivo();
    }

    public FuncionarioPersistencia getFuncionarioPersistencia() {
        return funcionarioPersistencia;
    }

    public void atualizarListaFuncionarios(){
        funcionarios = funcionarioPersistencia.carregarDoArquivo();
    }

    public ArrayList<Funcionario> getFuncionarios(){
        return new ArrayList<>(funcionarios);
    }
}
