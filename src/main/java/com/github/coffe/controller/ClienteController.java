package com.github.coffe.controller;

import com.github.coffe.model.entidades.Cliente;

import java.io.*;
import java.util.ArrayList;

public class ClienteController {
    ArrayList<Cliente> clientes = new ArrayList<>();
    File clientesFile = new File("src/main/java/com/github/coffe/dados/clientes.txt");
    Cliente usuario;
    private final String FILE_NAME = "src/main/java/com/github/coffe/dados/clientes.txt";

    //Construtor
    public ClienteController() {
        clientes = carregarCliente();
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
        atualizarClientes();
    }

    public void excluirCliente(){
        clientes.remove(usuario);
        atualizarClientes();
    }

    public void logarCliente(Cliente c){
        this.usuario = c;
    }

    //Cadastro e Login
    public void cadastrarCliente(String cpf, String email, String nome, String senha){
        Cliente novoCliente = new Cliente(cpf, email, nome, senha);
        clientes.add(novoCliente);
        atualizarClientes();
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

    //Arquivo.txt
    public void atualizarClientes(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.clientesFile, true))){
            for(Cliente c : this.clientes){
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e){
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public ArrayList<Cliente> carregarCliente(){
        if (!clientesFile.exists()){
            File clientesFile = new File("src/main/java/com/github/coffe/dados/clientes.txt");
        }
        try(BufferedReader br = new BufferedReader(new FileReader(this.clientesFile))){
            String linha;
            while ((linha = br.readLine()) != null){
                this.clientes.add(Cliente.fromString(linha));
            }
        } catch (IOException ignored){}
        return clientes;
    }
}
