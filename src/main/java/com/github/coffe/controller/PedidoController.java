package com.github.coffe.controller;

import com.github.coffe.model.servicos.Pedido;
import com.github.coffe.persistencia.PedidoPersistencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PedidoController {
    private static final Logger log = LogManager.getLogger(PedidoController.class);
    private final ArrayList<Pedido> pedidos;
    private ArrayList<Pedido> pendentes;
    private final PedidoPersistencia produtoPersistencia;

    public PedidoController(){
        produtoPersistencia = new PedidoPersistencia("src/main/java/com/github/coffe/dados/pedidos.txt");
        pedidos = produtoPersistencia.carregarDoArquivo();
        for (Pedido p: pedidos){
            if(p.getStatus().equals("Pendente")){
                pendentes.add(p);
            }
        }
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Pedido> getPendentes(){
        return pendentes;
    }

}
