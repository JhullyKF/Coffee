package com.github.coffe.view;

import com.github.coffe.controller.Autenticador;
import com.github.coffe.controller.FuncionarioController;
import com.github.coffe.model.entidades.Funcionario;

import java.util.Scanner;

public class FuncionarioView {
    private final FuncionarioController fc = new FuncionarioController();
    private final Scanner sc = new Scanner(System.in);
    private final Autenticador<Funcionario> autenticador = new Autenticador(fc.getFuncionarios());

    public void loginFuncionario(){
        int op;
        do {
            System.out.print("Insira seu CPF ou Email: ");
            String identificador = sc.nextLine();
            System.out.print("Insira sua senha: ");
            String senha = sc.nextLine();

            if (autenticador.autenticar(identificador, senha)) {
                return;
            }

            System.out.println("Credenciais inválidas!");
            System.out.println("Deseja tentar novamente? [1] sim / [0] não");
            op = Integer.parseInt(sc.nextLine());
        } while (op !=0);
    }

    public void listarFuncionarios(){
        fc.atualizarListaFuncionarios();
        for(Funcionario f: fc.getFuncionarios()){
            f.exibirDados();
        }
    }
}


