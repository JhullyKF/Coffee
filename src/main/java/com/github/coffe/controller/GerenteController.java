package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Vendedor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GerenteController {
    private static final Logger log = LogManager.getLogger(GerenteController.class);
    FuncionarioController funcionarioController = new FuncionarioController();

    public void cadastrarVendedor(Vendedor v){
        funcionarioController.funcionarios.add(v);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(funcionarioController.funcionariosFile))){
            for(Funcionario f : funcionarioController.funcionarios){
                bw.write(f.toString());
                bw.newLine();
            }
        } catch (IOException e){
            log.warn("Erro ao salvar funcionario " + v.getNome() + " - " + e.getMessage());

        }
    }
}
