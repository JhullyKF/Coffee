package com.github.coffe.view;

import com.github.coffe.controller.ClienteController;
import com.github.coffe.controller.PedidoController;
import com.github.coffe.controller.ProdutoController;
import com.github.coffe.model.entidades.Cliente;

import java.util.Scanner;

public class ClienteView {
    private final Scanner sc = new Scanner(System.in);
    private final ClienteController cc = new ClienteController();
    private final PedidoController pec = new PedidoController(this.cc);
    private final ProdutoController prc = new ProdutoController();
    private int id, qtd;

    //Menus
    public void menuClienteAcesso(){
        do{
            System.out.println("\n[1] - Login");
            System.out.println("[2] - Cadastrar");
            System.out.println("[0] - Sair\n");
            int opc = Integer.parseInt(sc.nextLine());
            if(opc == 1){
                loginCliente();
            } else if(opc == 2){
                autoCadastro();
            } else if(opc==0){
                return;
            } else{
                System.out.println("\nEntrada inválida! Tente novamente");
            }
        }while (true);
    }

    public void mainMenuCliente(){
        do {
            System.out.println("\nSelecione uma opção para continuarmos: ");
            System.out.println("[1] - Realizar pedido");
            System.out.println("[2] - Informações pessoais");
            System.out.println("[3] - Editar dados");
            System.out.println("[4] - Excluir Conta");
            System.out.println("[0] - Sair\n");
            int opc = Integer.parseInt(sc.nextLine());
            switch (opc){
                case 1: realizarPedido();
                case 2:
                    System.out.println("\n============ Seus dados =============");
                    cc.exibirCliente();
                    break;
                case 3: editarDados();
                    break;
                case 4:
                    System.out.println("\nDeseja mesmo excluir sua conta?");
                    System.out.println("[1] - Sim / [2] - Não\n");
                    int decisor = Integer.parseInt(sc.nextLine());
                    if(decisor == 1){
                        cc.excluirCliente();
                        return;
                    } else if(decisor != 2){
                        System.out.println("\nEntrada inválida! Exclusão abortada");
                    }
                    break;
                case 0: return;
                default: System.out.println("\nEntrada inválida! Tente novamente");
            }
        }while(true);
    }

    public void realizarPedido(){
        do {
            System.out.println("\nSelecione uma opção para continuarmos: ");
            System.out.println("[1] - Adicionar item ao carrinho");
            System.out.println("[2] - Vizualizar carrinho");
            System.out.println("[3] - retirar item do carrinho");
            System.out.println("[4] - finalizar pedido");
            System.out.println("[5] - vizualizar pedidos");
            System.out.println("[6] - listar produtos");
            System.out.println("[0] - Sair\n");
            int opc = Integer.parseInt(sc.nextLine());
            switch (opc){
                case 1:
                    System.out.println("Qual item deseja comprar(id): ");
                    id = Integer.parseInt(sc.nextLine());
                    System.out.println("Quantas unidades do produto: ");
                    qtd = Integer.parseInt(sc.nextLine());
                    if(pec.addItem(id, qtd)){
                        System.out.println("\nItem adicionado ao carrinho!");
                        break;
                    }
                    System.err.println("\nid ou quantidade inválidos!");
                    break;
                case 2:
                    if(pec.carrinhoVazio()){
                        System.err.println("\nCarrinho vazio!");
                        break;
                    }
                    System.out.println("\n============ Seu carrinho =============");
                    pec.exibirCarrinho();
                    break;
                case 3:
                    System.out.println("\nDigite o ID do produto: ");
                    id = Integer.parseInt(sc.nextLine());
                    System.out.println("\nInforme a quantidade a ser retirada desse produto: ");
                    qtd = Integer.parseInt(sc.nextLine());
                    if(pec.removerItem(id, qtd)){
                        System.out.println("\nCarrinho alterado!");
                        break;
                    }
                    System.err.println("Verifique o id do produto ou a quantidade a ser retirada");
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    if(prc.listarProdutos()){
                        break;
                    }
                    System.err.println("\nSem produtos no momento!");
                    break;
                case 0: return;
                default: System.out.println("\nEntrada inválida! Tente novamente");
            }
        }while(true);
    } //opção 1 de mainMenuCliente

    public void editarDados(){
        do{
            System.out.println("\nSelecione uma opção:");
            System.out.println("[1] - Editar nome");
            System.out.println("[2] - Editar email");
            System.out.println("[3] - Editar senha");
            System.out.println("[0] - Sair\n");
            int opc = Integer.parseInt(sc.nextLine());
            switch(opc){
                case 1:
                    System.out.println("\nInsira o nome:");
                    String novoNome  = sc.nextLine();
                    cc.editarDadoCliente("nome", novoNome);
                    break;
                case 2:
                    System.out.println("\nInsira o email:");
                    String novoEmail = sc.nextLine();
                    cc.editarDadoCliente("email", novoEmail);
                    break;
                case 3:
                    System.out.println("\nInsira a senha:");
                    String novaSenha = sc.nextLine();
                    cc.editarDadoCliente("senha", novaSenha);
                    break;
                case 0: return;
                default:System.out.println("\nEntrada inválida! Tente novamente");
            }
        } while(true);
    } //opção 3 de mainMenuCliente

    //Login e cadastro
    public void loginCliente(){
        do {
            System.out.println("\nInforme seus dados:");
            System.out.println("\nCPF ou Email: ");
            String identificador = sc.nextLine();
            System.out.println("Senha: ");
            String senha = sc.nextLine();
            Cliente cliente = cc.verificarCliente(identificador, senha);
            if(cliente != null){
                cc.logarCliente(cliente);
                mainMenuCliente();
                return;
            } else{
                System.out.println("\nUsuário ou senha inválidos");
                System.out.println("[1] - Tentar novamente");
                System.out.println("[0] - Sair");
                int opc = Integer.parseInt(sc.nextLine());
                if(opc == 0) return;
                else if (opc!=1){
                    System.out.println("\nEntrada inválida! Tente novamente");
                }
            }
        }while(true);
    }

    public void autoCadastro(){
        System.out.println("\nDigite seu nome:");
        String nome  = sc.nextLine();
        System.out.println("Digite seu cpf:");
        String cpf = sc.nextLine();
        System.out.println("Digite seu email:");
        String email = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
        cc.cadastrarCliente(cpf, email, nome, senha);
    }
}
