package com.github.coffe.controller;

import com.github.coffe.model.entidades.Cliente;

import java.util.ArrayList;

public class ClienteController {
    ArrayList<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(String cpf, String email, String nome, String senha){
        Cliente novoCliente = new Cliente(cpf, email, nome, senha);
        clientes.add(novoCliente);
    }
}
