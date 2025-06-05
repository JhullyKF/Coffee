package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Vendedor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GerenteController {
    private static final Logger log = LogManager.getLogger(GerenteController.class);
    private final FuncionarioController funcionarioController;

    public GerenteController(){
        funcionarioController = new FuncionarioController();
    }

    public boolean cadastrarVendedor(Vendedor v){
        funcionarioController.getFuncionarios().add(v);
        return true;
    }

    public boolean demitirFuncionario(Funcionario f){
        funcionarioController.getFuncionarios().remove(f);

        if(!funcionarioController.getFuncionarios().contains(f)) {
            log.info("Funcionario " + f.getNome() + "(ID : )" + f.getIdFuncionario() + " demitido com sucesso");
            return true;
        }
        log.error("Erro ao demitir funcionario " + f.getNome() + "(ID : )" + f.getIdFuncionario());
        return false;
    }

    public boolean alterarSalario(Funcionario f, double salario){
        f.setSalario(salario);
        return funcionarioController.getFuncionarios().contains(f);
    }
}
