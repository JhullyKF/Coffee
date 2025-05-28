package com.github.coffe.controller;

import com.github.coffe.model.entidades.Cliente;

import java.util.ArrayList;

public class ClienteController {
    ArrayList<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(String cpf, String email, String nome, String senha){
        Cliente novoCliente = new Cliente(cpf, email, nome, senha);
        clientes.add(novoCliente);
    }

    public boolean verificarCliente(String identificador, String senha){
        for (Cliente c : clientes) {
            if (c.getCpf().equals(identificador) ||
                    (c.getEmail().equals(identificador)) &&
                            c.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
