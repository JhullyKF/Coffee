package com.github.coffe.view;

import com.github.coffe.controller.ClienteController;
import com.github.coffe.model.entidades.Cliente;

import java.util.Scanner;

public class ClienteView {
    Scanner sc = new Scanner(System.in);
    ClienteController cc = new ClienteController();

    //Menus
    public void menuClienteAcesso(){
        do{
            System.out.println("\n[1] - Login");
            System.out.println("[2] - Cadastrar");
            System.out.println("[0] - Sair\n");
            int opc = Integer.parseInt(sc.nextLine());
            if(opc == 1){
                loginCliente();
            } else if(opc == 2){
                autoCadastro();
            } else if(opc==0){
                return;
            } else{
                System.out.println("\nEntrada inválida! Tente novamente");
            }
        }while (true);
    }

    public void mainMenuCliente(){
        do {
            System.out.println("\nSelecione uma opção para continuarmos: ");
            System.out.println("[1] - Realizar pedido");
            System.out.println("[2] - Informações pessoais");
            System.out.println("[3] - Editar dados");
            System.out.println("[4] - Excluir Conta");
            System.out.println("[0] - Sair\n");
            int opc = Integer.parseInt(sc.nextLine());
            switch (opc){
                case 1: realizarPedido();
                case 2:
                    System.out.println("\n============ Seus dados =============");
                    cc.exibirCliente();
                    break;
                case 3: editarDados();
                    break;
                case 4:
                    System.out.println("\nDeseja mesmo excluir sua conta?");
                    System.out.println("[1] - Sim / [2] - Não\n");
                    int decisor = Integer.parseInt(sc.nextLine());
                    if(decisor == 1){
                        cc.excluirCliente();
                        return;
                    } else if(decisor != 2){
                        System.out.println("\nEntrada inválida! Exclusão abortada");
                    }
                    break;
                case 0: return;
                default: System.out.println("\nEntrada inválida! Tente novamente");
            }
        }while(true);
    }

    public void realizarPedido(){
        do {
            switch (opc){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0: return;
                default: System.out.println("\nEntrada inválida! Tente novamente");
            }
        }while(true);
    } //opção 1 de mainMenuCliente

    public void editarDados(){
        do{
            System.out.println("\nSelecione uma opção:");
            System.out.println("[1] - Editar nome");
            System.out.println("[2] - Editar email");
            System.out.println("[3] - Editar senha");
            System.out.println("[0] - Sair\n");
            int opc = Integer.parseInt(sc.nextLine());
            switch(opc){
                case 1:
                    System.out.println("\nInsira o nome:");
                    String novoNome  = sc.nextLine();
                    cc.editarDadoCliente("nome", novoNome);
                    break;
                case 2:
                    System.out.println("\nInsira o email:");
                    String novoEmail = sc.nextLine();
                    cc.editarDadoCliente("email", novoEmail);
                    break;
                case 3:
                    System.out.println("\nInsira a senha:");
                    String novaSenha = sc.nextLine();
                    cc.editarDadoCliente("senha", novaSenha);
                    break;
                case 0: return;
                default:System.out.println("\nEntrada inválida! Tente novamente");
            }
        } while(true);
    } //opção 3 de mainMenuCliente

    //Login e cadastro
    public void loginCliente(){
        do {
            System.out.println("\nInforme seus dados:");
            System.out.println("\nCPF ou Email: ");
            String identificador = sc.nextLine();
            System.out.println("Senha: ");
            String senha = sc.nextLine();
            Cliente cliente = cc.verificarCliente(identificador, senha);
            if(cliente != null){
                cc.logarCliente(cliente);
                mainMenuCliente();
                return;
            } else{
                System.out.println("\nUsuário ou senha inválidos");
                System.out.println("[1] - Tentar novamente");
                System.out.println("[0] - Sair");
                int opc = Integer.parseInt(sc.nextLine());
                if(opc == 0) return;
                else if (opc!=1){
                    System.out.println("\nEntrada inválida! Tente novamente");
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
