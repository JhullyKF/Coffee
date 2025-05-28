package com.github.coffe.view;

import com.github.coffe.controller.ClienteController;
import com.github.coffe.model.entidades.Cliente;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.Scanner;

public class ClienteView {
    Scanner sc = new Scanner(System.in);
    ClienteController cc = new ClienteController();

    public void menuClienteAcesso(){
        do{
            System.out.println("[1] - Login");
            System.out.println("[2] - Cadastrar");
            System.out.println("[0] - Sair");
            int opc = sc.nextInt();
            if(opc == 1){
                loginCliente();
            } else if(opc == 2){
                autoCadastro();
            } else if(opc==0){
                return;
            } else{
                System.out.println("Entrada inválida! Tente novamente");
            }
        }while (true);
    }

    public void loginCliente(){
        System.out.println("\nInforme seus dados:");
        System.out.println("\nCPF ou Email: ");
        String identificador = sc.nextLine();
        System.out.println("\nSenha: ");
        String senha = sc.nextLine();
    }

    public void autoCadastro(){
        System.out.println("\nDigite seu nome:");
        String nome  = sc.nextLine();
        System.out.println("Digite seu cpf:");
        String cpf = sc.nextLine();
        System.out.println("Digite seu email:");
        String email = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
        cc.cadastrarCliente(cpf, email, nome, senha);
    }
}
