package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Gerente;
import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.view.GerenteView;
import com.github.coffe.view.VendedorView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class AutenticadorFuncionarios {
    private static final Logger log = LogManager.getLogger(AutenticadorFuncionarios.class);

    private final ArrayList<Funcionario> funcionarios;

    public AutenticadorFuncionarios(ArrayList<Funcionario> funcionarios){
        this.funcionarios = funcionarios;
    }

    public boolean autenticar(String cpf, String senha){
        for(Funcionario f: funcionarios){
            if (f.getCpf().equals(cpf) && f.getSenha().equals(senha)){
                log.info("Funcionario {} autenticado com sucesso", f.getIdFuncionario());
                logar(f);
                return true;
            }
        }
        log.warn("Falha ao autenticar funcionario");
        return false;
    }

    public void logar(Funcionario f){
        if (f instanceof Vendedor){
            VendedorController vendedorController = new VendedorController();
            vendedorController.loginVendedor(f);
            VendedorView vendedorView = new VendedorView(vendedorController);
            vendedorView.menuVendedor();
            return;
        } else if(f instanceof Gerente){
            GerenteController gerenteController = new GerenteController();
            gerenteController.loginGerente(f);
            GerenteView gerenteView = new GerenteView(gerenteController);
            gerenteView.opcoesGerencia();
            return;
        }
        log.warn("Falha ao logar funcionario {}", f.getIdFuncionario());
    }



}
