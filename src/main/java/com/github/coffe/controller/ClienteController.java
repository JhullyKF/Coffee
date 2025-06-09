package com.github.coffe.controller;

import com.github.coffe.model.entidades.Cliente;
import com.github.coffe.persistencia.ClientePersistencia;
import com.github.coffe.utils.Validador;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ClienteController{
    private final ArrayList<Cliente> clientes;
    private final ClientePersistencia clientePersistencia;
    private static final Logger log = LogManager.getLogger(ClienteController.class);
    private Cliente usuario;
    private final Validador validador = new Validador();

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

    public boolean editarDadoCliente(String dado, String dadoAtualizado){
        boolean valido = true;
        switch (dado){
            case "nome":
                log.info("Nome do cliente <{}> atualizado de \"{}\" para \"{}\"", usuario.getId_Cliente(), usuario.getNome(), dadoAtualizado);
                usuario.setNome(dadoAtualizado);
                break;
            case "email":
                if (!validador.validarEmail(dadoAtualizado) || validador.emailExistente(clientes, dadoAtualizado)) {
                    log.warn("Atualização de email do cliente <{}> mal sucedida \"{}\" -> email inválido", usuario.getId_Cliente(), dadoAtualizado);
                    valido = false;
                    break;
                }
                log.info("Email do cliente <{}> atualizado de \"{}\" para \"{}\"", usuario.getId_Cliente(), usuario.getEmail(), dadoAtualizado);
                usuario.setEmail(dadoAtualizado);
                break;
            case "senha":
                log.info("Senha do cliente <{}> atualizado de \"{}\" para \"{}\"", usuario.getId_Cliente(), usuario.getSenha(), dadoAtualizado);
                usuario.setSenha(dadoAtualizado);
                break;
        }
        if(valido){
            clientePersistencia.salvarEmArquivo(clientes);
            return valido;
        }
        return valido;
    }

    public void excluirCliente(){
        clientes.remove(usuario);
        clientePersistencia.salvarEmArquivo(clientes);
        log.info("Cliente com ID <{}> removeu sua conta com sucesso.", usuario.getId_Cliente());
    }

    public void logarCliente(Cliente c){
        this.usuario = c;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    //Cadastro e Login
    public boolean cadastrarCliente(String cpf, String email, String nome, String senha){
        if (!validador.validarCPF(cpf) || validador.cpfExistente(clientes, cpf)) {
            log.warn("Erro ao cadastrar usuário, dado inválido: {}", cpf);
            return false;
        }
        if (!validador.validarEmail(email) || validador.emailExistente(clientes, email)) {
            log.warn("Erro ao cadastrar usuário, dado inválido: {}", email);
            return false;
        }
        Cliente novoCliente = new Cliente(cpf, email, nome, senha);
        clientes.add(novoCliente);
        clientePersistencia.salvarEmArquivo(clientes);
        log.info("Sucesso ao cadastrar novo cliente!");
        return true;
    }

    public Cliente verificarCliente(String identificador, String senha){
        for (Cliente c : clientes) {
            if (c.getCpf().equals(identificador) ||
                    (c.getEmail().equals(identificador)) &&
                            c.getSenha().equals(senha)) {
                log.info("Login realizado pelo cliente {}", c.getNome());
                return c;
            }
        }
        log.warn("Tentativa de login mal sucedida para o usuário {}", identificador);
        return null;
    }

    public boolean removerCliente(int id){
        for (Cliente c : clientes){
            if(c.getId_Cliente() == id){
                clientes.remove(c);
                clientePersistencia.salvarEmArquivo(clientes);
                if (!clientes.contains(c)){
                    log.info("Remoção do cliente de ID: <{}> bem sucedida", id);
                    return true;
                }
            }
        }
        log.warn("Erro ao remover usuário, ID inválido");
        return false;
    }
}
