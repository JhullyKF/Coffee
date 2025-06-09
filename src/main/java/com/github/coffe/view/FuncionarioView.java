package com.github.coffe.view;

import com.github.coffe.controller.FuncionarioController;
import com.github.coffe.controller.GerenteController;
import com.github.coffe.controller.VendedorController;
import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Gerente;
import com.github.coffe.model.entidades.Vendedor;

import java.util.Scanner;

public class FuncionarioView {
    private final FuncionarioController funcionarioController = new FuncionarioController();
    private final Scanner sc = new Scanner(System.in);

    public void loginFuncionario(){
        int op=1;
        do{
            System.out.print("Insira seu CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Insira sua senha: ");
            String senha = sc.nextLine();

            Funcionario funcionario = funcionarioController.verificaLogin(cpf, senha);

            if(funcionario == null) {
                System.out.println("Credenciais inválidas!");
                System.out.println("Deseja tentar novamente? [1] sim / [0] não");
                op = Integer.parseInt(sc.nextLine());
            } else {
                switch (funcionario.getCargo()) {
                    case "Gerente":
                        GerenteView gerenteView = new GerenteView();
                        GerenteController gerenteController = new GerenteController();
                        gerenteController.loginGerente((Gerente) funcionario);
                        gerenteView.opcoesGerencia();
                        return;

                    case "Vendedor":
                        VendedorController vendedorController = new VendedorController();
                        vendedorController.loginVendedor(funcionario);
                        VendedorView vendedorView = new VendedorView(vendedorController);
                        vendedorView.menuVendedor(); //
                        return;

                    default:
                        System.out.println("Entrada inválida!");
                        return;
                }
            }
        } while (op != 0);

    }
}
