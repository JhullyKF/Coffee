package com.github.coffe.view;

import java.util.Scanner;

public class MainView {
    FuncionarioView funcionarioView = new FuncionarioView();
    Scanner sc = new Scanner(System.in);

    public void inicializar(){
        int op = 1;
        do{
            System.out.println("Insira uma opção para continuar: ");
            System.out.println("[1] - Cliente");
            System.out.println("[2] - Funcionário");
            System.out.println("[0] - Sair");
            op = sc.nextInt();
            switch (op){
                case 1:
                    System.out.println("[1] - Login");
                    System.out.println("[2] - Cadastrar");
                    if (sc.nextInt() == 1){
                        loginCliente();
                    } else if (sc.nextInt() == 2){
                        autoCadastro();
                    } else {
                        System.out.println("Entrada inválida");
                        return;
                    }
                    break;

                case 2: funcionarioView.loginFuncionario(); break;
                default:
                    System.out.println("Entrada inválida! Tente novamente"); break;
            }
        } while(op != 0);
    }


}
