package com.github.coffe.view;

import com.github.coffe.view.FuncionarioView;

import java.io.IOException;
import java.util.Scanner;

public class MainView {
    FuncionarioView funcionarioView = new FuncionarioView();
    Scanner sc = new Scanner(System.in);
    ClienteView cv = new ClienteView();
    int op = 1;

    public void inicializar(){
        mensagemBoasVindas();
        do{
            System.out.println("\nInsira uma opção para continuar: ");
            System.out.println("\n[1] - Cliente");
            System.out.println("[2] - Funcionário");
            System.out.println("[0] - Sair\n");
            op = Integer.parseInt(sc.nextLine().trim());
            switch (op){
                case 0:
                    System.out.println("\nEncerrando...");
                    System.out.println("\nVolte sempre!!");
                    return;
                case 1: cv.menuClienteAcesso(); break;
                case 2: funcionarioView.loginFuncionario(); break;
                default:
                    System.out.println("\nEntrada inválida! Tente novamente");
            }
        } while(op != 0);
    }

    public void mensagemBoasVindas() {

        System.out.println("""
                
                        ╔════════════════════════════════════════════╗
                        ║                                            ║
                        ║        ☕  BEM-VINDO AO COFFEE  ☕           ║
                        ║                                            ║
                        ╚════════════════════════════════════════════╝
                """);
    }
}
