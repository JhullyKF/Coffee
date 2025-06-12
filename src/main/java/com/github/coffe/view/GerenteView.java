package com.github.coffe.view;

import com.github.coffe.controller.ClienteController;
import com.github.coffe.controller.FuncionarioController;
import com.github.coffe.controller.GerenteController;
import com.github.coffe.controller.ProdutoController;
import com.github.coffe.model.entidades.Cliente;
import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.model.servicos.Produto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class GerenteView {
    private static final Logger log = LogManager.getLogger(GerenteView.class);

    private final Scanner sc = new Scanner(System.in);
    private final FuncionarioController funcionarioController = new FuncionarioController();
    private final GerenteController gerenteController;
    private final ProdutoController produtoController = new ProdutoController();
    private final ClienteController clienteController = new ClienteController();
    private final ProdutoView produtoView = new ProdutoView();

    public GerenteView(GerenteController gc){
        gerenteController = gc;
    }
    //Menu de Gerencia principal
    public void opcoesGerencia(){
        int op;
        while(true){
            System.out.println("======  Selecione uma opção:  =======");
            System.out.println("[1] - Gerenciar funcionarios"); //ok
            System.out.println("[2] - Gerenciar clientes");     //ok
            System.out.println("[3] - Gerenciar produtos");     //ok
            System.out.println("[0] - voltar");                 //ok
            op = Integer.parseInt(sc.nextLine());
            switch (op){
                case 0: return;
                case 1: gerenciarFuncionarios(); break;
                case 2: gerenciarClientes(); break;
                case 3: gerenciarProdutos(); break;
                default:
                    System.out.println("Entrada invalida! Tente novamente"); break;
            }
        }
    }

    public void gerenciarFuncionarios(){
        int op;
        while (true) {
            for (int i = 0; i<3; i++) {
                System.out.println(".");
                try{
                    sleep(200);
                } catch (InterruptedException e){
                    log.error("Delay com erro: ", e);
                }
            }

            System.out.println("==== Selecione uma opção: =====");
            System.out.println("[1] - Listar funcionario");          //ok
            System.out.println("[2] - Cadastrar novo vendedor");     //ok
            System.out.println("[3] - Demitir funcionario");         //ok
            System.out.println("[4] - Alterar salários");            //ok
            System.out.println("[0] - Voltar");                      //ok
            op = Integer.parseInt(sc.nextLine());

            switch (op){
                case 0: return;
                case 1: listarFuncionarios(); break;
                case 2: cadastrarVendedor(); break;
                case 3: demitirFuncionario(); break;
                case 4: alterarSalario(); break;
                default:
                    System.out.println("Entrada inválida, tente novamente!");
            }
        }
    }

    public void gerenciarClientes() {
        int op = 1;
        while (true) {
            for (int i = 0; i<3; i++) {
                System.out.println(".");
                try{
                    sleep(200);
                } catch (InterruptedException e){
                    log.error("Delay com erro: ", e);
                }
            }

            System.out.println("==== Selecione uma opção: =====");
            System.out.println("[1] - Listar clientes");            //ok
            System.out.println("[2] - Excluir clientes");           //ok
            System.out.println("[0] - Voltar");                     //ok
            op = Integer.parseInt(sc.nextLine());

            switch (op){
                case 0: return;
                case 1: listarClientes(); break;
                case 2: removerCliente(); break;
                default:
                    System.out.println("Entrada inválida, tente novamente!");
            }
        }
    }

    public void gerenciarProdutos(){
        int op;
        while (true){
            for (int i = 0; i<3; i++) {
                System.out.println(".");
                try{
                    sleep(200);
                } catch (InterruptedException e){
                    log.error("Delay com erro: ", e);
                }
            }

            System.out.println("==== Selecione uma opção: =====");
            System.out.println("[1] - Listar produtos");      //ok
            System.out.println("[2] - Cadastrar produto");    //ok
            System.out.println("[3] - Excluir produto");     //ok
            System.out.println("[4] - Alterar produtos");        //ok
            System.out.println("[0] - Voltar");
            op = Integer.parseInt(sc.nextLine());

            switch (op){
                case 0: return;
                case 1: produtoView.listarProdutos(); break;
                case 2: addProduto(); break;
                case 3: removerProduto(); break;
                case 4: alterarProduto(); break;
                default:
                    System.out.println("Entrada inválida, tente novamente!");
            }
        }
    }

    //Funções de gerenciar funcionarios
    public void listarFuncionarios(){
        for(Funcionario f: funcionarioController.getFuncionarios()){
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
        boolean result = gerenteController.cadastrarVendedor(new Vendedor(nome, email, cpf));

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
        for(Funcionario f: funcionarioController.getFuncionarios()){
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
        if (result) {
            System.out.println("Funcionario demitido com sucesso");
            return;
        }
        System.out.println("Erro ao demitir funcionario");
    }

    public void alterarSalario(){
        boolean result = false;
        System.out.println("Insira o id do funcionario para alteração: ");
        int id = Integer.parseInt(sc.nextLine());
        for(Funcionario f: funcionarioController.getFuncionarios()){
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

    //Funções de gerenciar produtos

    public void addProduto(){
        int op = 1;
        do{
            System.out.println("Insira o nome do produto: ");
            String nome = sc.nextLine();
            System.out.println("Insira uma breve descrição: ");
            String desc = sc.nextLine();
            System.out.println("Insira o preço: ");
            double valor = Double.parseDouble(sc.nextLine());
            System.out.println("Insira o estoque: ");
            int estoque = Integer.parseInt(sc.nextLine());
            boolean result = produtoController.addProduto(nome, desc, valor, estoque);

            if (!result){
                System.out.println("Erro ao cadastrar o produto");
                return;
            }

            System.out.println("Deseja adicionar mais um produto? [1] - sim / [0] - não");
            op = Integer.parseInt(sc.nextLine());

        } while(op!=0);
        System.out.println("Encerrando processo...");
    }

    public void removerProduto(){
        int op = 1;
        do {
            System.out.println("Insira o ID do produto: ");
            int id = Integer.parseInt(sc.nextLine());

            boolean result = produtoController.removerProduto(id);

            if (!result) {
                System.out.println("Erro ao remover produto");
                return;
            }

            System.out.println("Deseja adicionar mais um produto? [1] - sim / [0] - não");
            op = Integer.parseInt(sc.nextLine());

        } while(op!=0);
        System.out.println("Encerrando processo...");
    }

    public void alterarProduto(){
        int op = 1;
        do {
            System.out.println("Insira o ID do produto: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Digite a opção a ser alterada: ");
            System.out.println("[1] - Nome");
            System.out.println("[2] - Descrição");
            System.out.println("[3] - Valor");
            System.out.println("[4] - Estoque");
            System.out.println("[0] - Sair");
            op = Integer.parseInt(sc.nextLine());
            boolean result = false;
            switch (op) {
                case 1:
                    System.out.println("Informe o novo nome: ");
                    String nome = sc.nextLine();
                    result = produtoController.alterarProduto(id, op, nome);
                    break;

                case 2:
                    System.out.println("Informe uma nova descrição: ");
                    String desc = sc.nextLine();
                    result = produtoController.alterarProduto(id, op, desc);
                    break;

                case 3:
                    System.out.println("Informe o novo valor: ");
                    String valor = sc.nextLine();
                    result = produtoController.alterarProduto(id, op, valor);
                    break;

                case 4:
                    System.out.println("Informe o novo estoque: ");
                    String estoque = sc.nextLine();
                    result = produtoController.alterarProduto(id, op, estoque);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Entrada inválida"); break;
            }
            if (result){
                System.out.println("Alteração realizada");
            } else {
                System.out.println("Alteração não realizada");
            }
            System.out.println("Deseja alterar mais um produto? [1] - sim / [0] - não");
            op = Integer.parseInt(sc.nextLine());
        } while (op != 0);
        System.out.println("Encerrando processo...");
    }

    //Funções de gerenciar clientes
    public void listarClientes(){
        for(Cliente c: clienteController.getClientes()){
            c.exibirDadosGerencia();
        }
    }

    public void removerCliente(){
        int op = 1;
        do {
            System.out.println("Insira o ID do cliente: ");
            int id = Integer.parseInt(sc.nextLine());

            boolean result = clienteController.removerCliente(id);

            if (!result) {
                System.out.println("Erro ao remover cliente");
                return;
            }

            System.out.println("Deseja adicionar mais um cliente? [1] - sim / [0] - não");
            op = Integer.parseInt(sc.nextLine());

        } while(op!=0);
        System.out.println("Encerrando processo...");
    }
}
