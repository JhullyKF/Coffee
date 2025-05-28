package com.github.coffe.controller;

import com.github.coffe.model.entidades.Cliente;

import java.io.*;
import java.util.ArrayList;

public class ClienteController {
    ArrayList<Cliente> clientes = new ArrayList<>();
    File clientesFile = new File("clientes.txt");

    public ClienteController() {
        clientes = carregarCliente();
    }

    //Cadastro e Login
    public void cadastrarCliente(String cpf, String email, String nome, String senha){
        Cliente novoCliente = new Cliente(cpf, email, nome, senha);
        clientes.add(novoCliente);
        salvarCliente();
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

    //Arquivo.txt
    public void salvarCliente(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.clientesFile))){
            for(Cliente c : this.clientes){
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e){
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public ArrayList<Cliente> carregarCliente(){
        try(BufferedReader br = new BufferedReader(new FileReader(this.clientesFile))){
            String linha;
            while ((linha = br.readLine()) != null){
                this.clientes.add(Cliente.fromString(linha));
            }
        } catch (IOException ignored){}
        return clientes;
    }
}
