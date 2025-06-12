package com.github.coffe.controller;

import com.github.coffe.interfaces.Identificavel;
import com.github.coffe.model.entidades.Cliente;
import com.github.coffe.model.entidades.Gerente;
import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.view.ClienteView;
import com.github.coffe.view.GerenteView;
import com.github.coffe.view.VendedorView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Autenticador<T extends Identificavel>{
    private static final Logger log = LogManager.getLogger(Autenticador.class);

    private final ArrayList<T> list;

    public Autenticador(ArrayList<T> list){
        this.list = list;
    }

    public boolean autenticar(String identificador, String senha){
        for(T obj: list){
            if ((obj.getCpf().equals(identificador) || obj.getEmail().equals(identificador)) && obj.getSenha().equals(senha)){
                log.info("Usuario autenticado com sucesso");
                logar(obj);
                return true;
            }
        }
        log.warn("Falha ao autenticar usuário");
        return false;
    }

    public void logar(T obj){
        if (obj instanceof Vendedor){
            VendedorController vendedorController = new VendedorController();
            vendedorController.loginVendedor((Vendedor) obj);
            VendedorView vendedorView = new VendedorView(vendedorController);
            vendedorView.menuVendedor();
            return;
        } else if(obj instanceof Gerente){
            GerenteController gerenteController = new GerenteController();
            gerenteController.loginGerente((Gerente) obj);
            GerenteView gerenteView = new GerenteView(gerenteController);
            gerenteView.opcoesGerencia();
            return;
        } else if(obj instanceof Cliente) {
            ClienteController clienteController = new ClienteController();
            clienteController.logarCliente((Cliente) obj);
            ClienteView clienteView = new ClienteView();
            clienteView.mainMenuCliente();
            return;
        }
        log.warn("Falha ao logar usuario");
    }

}
