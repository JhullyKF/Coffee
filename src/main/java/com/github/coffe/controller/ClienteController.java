package com.github.coffe.controller;

import com.github.coffe.model.entidades.Cliente;
import com.github.coffe.persistencia.ClientePersistencia;

import java.util.ArrayList;

public class ClienteController{
    private final ArrayList<Cliente> clientes;
    private final ClientePersistencia clientePersistencia;
    private Cliente usuario;

    //Construtor
    public ClienteController() {
        clientePersistencia = new ClientePersistencia("src/main/java/com/github/coffe/dados/clientes.txt");
        clientes = clientePersistencia.carregarDoArquivo();
    }

    public ArrayList<Cliente> getClientes(){
        return clientes;
    }

    //"mainMenuCliente"
    public void exibirCliente(){
        usuario.exibirDados();
    }

    public void editarDadoCliente(String dado, String dadoAtualizado){
        switch (dado){
            case "nome":
                usuario.setNome(dadoAtualizado);
                break;
            case "email":
                usuario.setEmail(dadoAtualizado);
                break;
            case "senha":
                usuario.setSenha(dadoAtualizado);
                break;
        }
        clientePersistencia.salvarEmArquivo(clientes);
    }

    public void excluirCliente(){
        clientes.remove(usuario);
        clientePersistencia.salvarEmArquivo(clientes);
    }

    public void logarCliente(Cliente c){
        this.usuario = c;
    }

    //Cadastro e Login
    public void cadastrarCliente(String cpf, String email, String nome, String senha){
        Cliente novoCliente = new Cliente(cpf, email, nome, senha);
        clientes.add(novoCliente);
        clientePersistencia.salvarEmArquivo(clientes);
    }

    public Cliente verificarCliente(String identificador, String senha){
        for (Cliente c : clientes) {
            if (c.getCpf().equals(identificador) ||
                    (c.getEmail().equals(identificador)) &&
                            c.getSenha().equals(senha)) {
                return c;
            }
        }
        return null;
    }

    public boolean removerCliente(int id){
        for (Cliente c : clientes){
            if(c.getId_Cliente() == id){
                clientes.remove(c);
                clientePersistencia.salvarEmArquivo(clientes);
                if (!clientes.contains(c)){
                    return true;
                }
            }
        }
        return false;
    }
}
