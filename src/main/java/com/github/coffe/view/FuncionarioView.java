package com.github.coffe.view;

import com.github.coffe.controller.AutenticadorFuncionarios;
import com.github.coffe.controller.FuncionarioController;

import java.util.Scanner;

public class FuncionarioView {
    private final FuncionarioController fc = new FuncionarioController();
    private final Scanner sc = new Scanner(System.in);
    private final AutenticadorFuncionarios autenticador = new AutenticadorFuncionarios(fc.getFuncionarios());


    public void loginFuncionario(){
        int op;
        do {
            System.out.print("Insira seu CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Insira sua senha: ");
            String senha = sc.nextLine();

            if (autenticador.autenticar(cpf, senha)) {
                return;
            }

            System.out.println("Credenciais inválidas!");
            System.out.println("Deseja tentar novamente? [1] sim / [0] não");
            op = Integer.parseInt(sc.nextLine());
        } while (op !=0);
    }
}
