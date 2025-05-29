package com.github.coffe.view;

import com.github.coffe.controller.FuncionarioController;
import com.github.coffe.controller.GerenteController;
import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Vendedor;

import java.util.Scanner;

public class GerenteView {
    Scanner sc = new Scanner(System.in);
    FuncionarioController funcionarioController = new FuncionarioController();
    GerenteController gerenteController = new GerenteController();

    public void menuGerencia(){
        int op;
        do{
            op = opcoesGerencia();
            switch (op){
                case 1: cadastrarVendedor(); break;
                case 2: listarFuncionarios(); break;
               // case 3: gerenteController.demitirFuncionario();
            }

        }while (op != 0 || op < 10);
    }

    public int opcoesGerencia(){
        System.out.println("Selecione uma opção:");
        System.out.println("[1] - Cadastrar novo vendedor");
        System.out.println("[2] - Listar funcionarios");
        System.out.println("[3] - Demitir funcionario");
        System.out.println("[4] - Alterar salários");
        System.out.println("[5] - Adicionar produtos");
        System.out.println("[6] - Remover produtos");
        System.out.println("[7] - Alterar produto");
        System.out.println("[8] - Excluir cliente");
        System.out.println("[9] - Alterar dados do cliente");
        System.out.println("[0] - Deslogar");
        return Integer.parseInt(sc.nextLine());
    }

    public void listarFuncionarios(){
        funcionarioController.funcionarios.clear();
        funcionarioController.carregarFuncionarios();
        for(Funcionario f: funcionarioController.funcionarios){
            f.exibirDados();
        }
    }

    public void cadastrarVendedor(){
        System.out.println("Informe o nome do novo funcionário: ");
        String nome = sc.nextLine();
        System.out.println("Insira o CPF: ");
        String cpf = sc.nextLine();
        System.out.println("Insira o email: ");
        String email = sc.nextLine();
        int id = funcionarioController.atribuiId();
        gerenteController.cadastrarVendedor(new Vendedor(id, nome, email, cpf));
    }


}
