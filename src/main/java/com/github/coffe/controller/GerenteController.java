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

    public boolean cadastrarVendedor(Vendedor v){
        funcionarioController.funcionarios.add(v);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(funcionarioController.funcionariosFile))){
            for(Funcionario f : funcionarioController.funcionarios){
                bw.write(f.toString());
                bw.newLine();
            }
            log.info("Funcionário " + v.getNome() + " cadastrado com sucesso");
            return true;
        } catch (IOException e){
            log.warn("Erro ao salvar funcionario " + v.getNome() + " - " + e.getMessage());
            return false;
        }
    }

    public boolean demitirFuncionario(Funcionario f){
        funcionarioController.funcionarios.remove(f);
        funcionarioController.carregarFuncionarios();
        if(!funcionarioController.funcionarios.contains(f)) {

            log.info("Funcionario " + f.getNome() + "(ID : )" + f.getIdFuncionario() + " demitido com sucesso");
            return true;
        }
        log.error("Erro ao demitir funcionario " + f.getNome() + "(ID : )" + f.getIdFuncionario());
        return false;
    }

    public boolean alterarSalario(Funcionario f, double salario){
        f.setSalario(salario);
        funcionarioController.carregarFuncionarios();
        if (funcionarioController.funcionarios.contains(f)){
            return true;
        }
        return false;
    }



}
