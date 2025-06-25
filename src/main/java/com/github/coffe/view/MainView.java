package com.github.coffe.view;

import java.util.Scanner;

public class MainView {
    private final FuncionarioView funcionarioView = new FuncionarioView();
    private final Scanner sc = new Scanner(System.in);
    private final ClienteView cv = new ClienteView();


    public void inicializar(){
        int op;
        mensagemBoasVindas();
        while (true) {
            System.out.println("\nInsira uma opção para continuar: ");
            System.out.println("\n[1] - Cliente");
            System.out.println("[2] - Funcionário");
            System.out.println("[0] - Sair\n");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nEntrada inválida! Tente novamente");
                continue;
            }

            switch (op){
                case 0:
                    System.out.println("\nEncerrando...");
                    System.out.println("\nVolte sempre!!");
                    sc.close();
                    return;
                case 1: cv.menuClienteAcesso(); break;
                case 2: funcionarioView.loginFuncionario(); break;
                default:
                    System.out.println("\nEntrada inválida! Tente novamente");
            }
        }
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
