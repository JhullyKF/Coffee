package com.github.coffe.view;

import com.github.coffe.controller.FuncionarioController;
import com.github.coffe.controller.GerenteController;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;

public class FuncionarioView {
    FuncionarioController funcionarioController = new FuncionarioController();
    Scanner sc = new Scanner(System.in);

    public void loginFuncionario(){
        int op=1;
        do{
            System.out.print("Insira seu CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Insira sua senha: ");
            String senha = sc.nextLine();

            int tipo = funcionarioController.verificaLogin(cpf, senha);

             switch (tipo){
                 case 1:
                     GerenteView gerenteView = new GerenteView();
                     gerenteView.opcoesGerencia(); return;

                 case 2:
                     VendedorView vendedorView = new VendedorView();
                     vendedorView.menuVendedor(); break;

                 case 0:
                     System.out.println("Credenciais inválidas!");
                     System.out.println("Deseja tentar novamente? [1] sim / [0] não");
                     op = Integer.parseInt(sc.nextLine()); break;

                 default:
                     System.out.println("Entrada inválida!"); return;
             }
        } while (op != 0);

    }
}
