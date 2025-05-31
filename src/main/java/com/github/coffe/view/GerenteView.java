package com.github.coffe.view;

import com.github.coffe.controller.FuncionarioController;
import com.github.coffe.controller.GerenteController;
import com.github.coffe.controller.ProdutoController;
import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Vendedor;

import java.util.Scanner;

public class GerenteView {
    Scanner sc = new Scanner(System.in);
    FuncionarioController funcionarioController = new FuncionarioController();
    GerenteController gerenteController = new GerenteController();
    ProdutoController produtoController = new ProdutoController();

    public void menuGerencia(){
        int op;
        do{
            op = opcoesGerencia();
            switch (op){
                case 1: cadastrarVendedor(); break;
                case 2: listarFuncionarios(); break;
                case 3: demitirFuncionario(); break;
                case 4: alterarSalario(); break;
               // case 5: addProduto();
            }

        }while (op != 0 || op < 10);
    }

    public int opcoesGerencia(){
        System.out.println("Selecione uma opção:");
        System.out.println("[1] - Cadastrar novo vendedor"); //ok
        System.out.println("[2] - Listar funcionario");      //ok
        System.out.println("[3] - Demitir funcionario");     //ok
        System.out.println("[4] - Alterar salários");        //ok
        System.out.println("[5] - Adicionar produtos");      //->
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
        boolean result = gerenteController.cadastrarVendedor(new Vendedor(id, nome, email, cpf));
        if (result) {
            System.out.println("Funcionario cadastrado com sucesso");
            return;
        }

        System.out.println("Erro ao cadastrar funcionario");
    }

    public void demitirFuncionario(){
        boolean result = false;
        System.out.println("Insira o ID do funcionário para desligamento: ");
        int id = Integer.parseInt(sc.nextLine());
        for(Funcionario f: funcionarioController.funcionarios){
            if(f.getIdFuncionario() == id){
                System.out.println("====== Dados do funcionário: =======");
                f.exibirDados();
                System.out.println("Deseja confirmar a demissão? ");
                System.out.println("[1] - sim / [2] - não");
                switch (Integer.parseInt(sc.nextLine())){
                    case 1:  result = gerenteController.demitirFuncionario(f); break;
                    case 2:
                        System.out.println("Cancelando..."); return;
                    default:
                        System.out.println("Entrada inválida!"); return;
                }
            }
        }
        if (result)
            System.out.println("Funcionario demitido com sucesso");
        else
            System.out.println("Erro ao demitir funcionario");
    }

    public void alterarSalario(){
        boolean result = false;
        System.out.println("Insira o id do funcionario para alteração: ");
        int id = Integer.parseInt(sc.nextLine());
        for(Funcionario f: funcionarioController.funcionarios){
            if(f.getIdFuncionario() == id){
                System.out.println("====== Dados do funcionário: =======");
                f.exibirDados();
                System.out.println("Informe o novo salário: ");
                double salario = sc.nextDouble();
                System.out.println("Deseja confirmar a alteração? ");
                System.out.println("[1] - sim / [2] - não");
                switch (Integer.parseInt(sc.nextLine())){
                    case 1: result = gerenteController.alterarSalario(f, salario); break;
                    case 2:
                        System.out.println("Cancelando..."); break;
                    default:
                        System.out.println("Entrada inválida!"); break;
                }
            }
        }
        if (result)
            System.out.println("Salario alterado com sucesso");
        else
            System.out.println("Erro ao alterar salário");
    }

    public void addProduto(){
        int op = 1;
        do{
            int id = produtoController.atribuiId();
            System.out.println("Insira o nome do produto: ");
            String nome = sc.nextLine();
            System.out.println("Insira o preço: ");
            double valor = sc.nextDouble();
            System.out.println("Insira o estoque: ");
            int estoque = Integer.parseInt(sc.nextLine());

        } while(op!=0);
    }

}
