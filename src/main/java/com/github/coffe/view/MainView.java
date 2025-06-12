package com.github.coffe.view;

import java.util.Scanner;

public class MainView {
    private final FuncionarioView funcionarioView = new FuncionarioView();
    private final Scanner sc = new Scanner(System.in);
    private final ClienteView cv = new ClienteView();


    public void inicializar(){
        int op;
        mensagemBoasVindas();
        do{
            System.out.println("\nInsira uma opção para continuar: ");
            System.out.println("\n[1] - Cliente");
            System.out.println("[2] - Funcionário");
            System.out.println("[0] - Sair\n");
            op = sc.nextInt();

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
        } while(true);
    }

    public void mensagemBoasVindas() {

        System.out.println("""
                
                        ╔════════════════════════════════════════════╗
                        ║                                             ║
                        ║        ☕  BEM-VINDO AO COFFEE  ☕           ║
                        ║                                             ║
                        ╚════════════════════════════════════════════╝
                """);
    }
}
