package com.github.coffe.view;

import com.github.coffe.controller.ClienteController;
import com.github.coffe.model.entidades.Cliente;

import java.util.Scanner;

public class ClienteView {
    Scanner sc = new Scanner(System.in);
    ClienteController cc = new ClienteController();

    public void menuClienteAcesso(){
        do{
            System.out.println("[1] - Login");
            System.out.println("[2] - Cadastrar");
            System.out.println("[0] - Sair");
            int opc = Integer.parseInt(sc.nextLine());
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

    public void mainMenuCliente(){
        do {
            System.out.println("Selecione uma opção para continuarmos: ");
            System.out.println("[1] - Realizar pedido");
            System.out.println("[2] - Informações pessoais");
            System.out.println("[3] - Editar dados");
            System.out.println("[4] - Excluir Conta");
            System.out.println("[0] - Sair");
            int opc = Integer.parseInt(sc.nextLine());
            switch (opc){
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 0: break;
                default: System.out.println("Entrada inválida! Tente novamente");
            }
        }while(true);
    }

    //Login e cadastro
    public void loginCliente(){
        do {
            System.out.println("Informe seus dados:");
            System.out.println("CPF ou Email: ");
            String identificador = sc.nextLine();
            System.out.println("Senha: ");
            String senha = sc.nextLine();
            Cliente cliente = cc.verificarCliente(identificador, senha);
            if(cliente != null){
                cc.logarCliente(cliente);
                mainMenuCliente();
                return;
            } else{
                System.out.println("Usuário ou senha inválidos");
                System.out.println("[1] - Tentar novamente");
                System.out.println("[0] - Sair");
                int opc = Integer.parseInt(sc.nextLine());
                if(opc == 0) return;
                else if (opc!=1){
                    System.out.println("Entrada inválida! Tente novamente");
                }
            }
        }while(true);
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
