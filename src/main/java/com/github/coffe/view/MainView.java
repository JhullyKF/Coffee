package com.github.coffe.view;

import com.github.coffe.controller.FuncionarioView;

import java.util.Scanner;

public class MainView {
    FuncionarioView funcionarioView = new FuncionarioView();
    Scanner sc = new Scanner(System.in);
    ClienteView cv = new ClienteView();

    public void inicializar(){
        int op = 1;
        do{
            System.out.println("Insira uma opção para continuar: ");
            System.out.println("[1] - Cliente");
            System.out.println("[2] - Funcionário");
            System.out.println("[0] - Sair");
            op = sc.nextInt();
            switch (op){
                case 0:
                    System.out.println("Encerrando...");
                    System.out.println("Volte sempre!!");
                    return;
                case 1: cv.menuClienteAcesso(); break;
                case 2: funcionarioView.loginFuncionario(); break;
                case 0: break;
                default:
                    System.out.println("Entrada inválida! Tente novamente");
            }
        } while(op != 0);
    }


}
