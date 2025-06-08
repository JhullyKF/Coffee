package com.github.coffe.view;

import com.github.coffe.controller.ClienteController;
import com.github.coffe.controller.PedidoController;
import com.github.coffe.controller.ProdutoController;
import com.github.coffe.model.entidades.Cliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ClienteView {
    private final Scanner sc = new Scanner(System.in);
    private final ClienteController clienteController = new ClienteController();
    private final PedidoController pedidoController = new PedidoController(this.clienteController);
    private final ProdutoController produtoController = new ProdutoController();
    private final PedidoView pedidoView = new PedidoView(pedidoController);
    private int id, qtd;
    private static final Logger log = LogManager.getLogger(ClienteView.class);

    //Menus
    public void menuClienteAcesso(){
        do{
            System.out.println("\n[1] - Login");
            System.out.println("[2] - Cadastrar");
            System.out.println("[0] - Sair\n");
            int opc = Integer.parseInt(sc.nextLine());
            switch (opc){
                case 1: loginCliente(); break;
                case 2: if(autoCadastro()) System.out.println("\nCadastro efetuado com sucesso"); break;
                case 0: return;
                default: System.out.println("\nEntrada inválida! Tente novamente");
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
                case 1: realizarPedido(); break;
                case 2:
                    System.out.println("\n============ Seus dados =============");
                    clienteController.exibirCliente();
                    break;
                case 3: editarDados(); break;
                case 4:
                    System.out.println("\nDeseja mesmo excluir sua conta?");
                    System.out.println("[1] - Sim / [2] - Não\n");
                    int decisor = Integer.parseInt(sc.nextLine());
                    if(decisor == 1){
                        clienteController.excluirCliente();
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
                    if(pedidoController.addItem(id, qtd)){
                        System.out.println("\nItem adicionado ao carrinho!");
                        break;
                    }
                    System.err.println("\nid ou quantidade inválidos!");
                    break;
                case 2:
                    pedidoView.exibirCarrinho();
                    break;
                case 3:
                    System.out.println("\nDigite o ID do produto: ");
                    id = Integer.parseInt(sc.nextLine());
                    System.out.println("\nInforme a quantidade a ser retirada desse produto: ");
                    qtd = Integer.parseInt(sc.nextLine());
                    if(pedidoController.removerItem(id, qtd)){
                        System.out.println("\nCarrinho alterado!");
                        break;
                    }
                    System.err.println("Verifique o id do produto ou a quantidade a ser retirada");
                    break;
                case 4:
                    if(pedidoController.finalizarPedido()){
                        System.out.println("\nPedido feito com sucesso!");
                        break;
                    }
                    System.err.println("\nVerifique se seu carrinho contém itens!");
                    break;
                case 5:
                    pedidoView.mostrarPedidosCliente();
                    break;
                case 6:
                    if(produtoController.listarProdutos()){
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
                    clienteController.editarDadoCliente("nome", novoNome);
                    break;
                case 2:
                    System.out.println("\nInsira o email:");
                    String novoEmail = sc.nextLine();
                    if(clienteController.editarDadoCliente("email", novoEmail)){
                        System.out.println("\nEmail atualizado com sucesso!");
                        break;
                    }
                    System.err.println("Email inválido ou já cadastrado!");
                    break;
                case 3:
                    System.out.println("\nInsira a senha:");
                    String novaSenha = sc.nextLine();
                    clienteController.editarDadoCliente("senha", novaSenha);
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
            Cliente cliente = clienteController.verificarCliente(identificador, senha);
            if(cliente != null){
                clienteController.logarCliente(cliente);
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

    public boolean autoCadastro(){
        System.out.println("\nDigite seu nome:");
        String nome  = sc.nextLine();
        System.out.println("Digite seu cpf:");
        String cpf = sc.nextLine();
        System.out.println("Digite seu email:");
        String email = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
        boolean valido = clienteController.cadastrarCliente(cpf, email, nome, senha);
        if (!valido) {
            System.err.println("\nCadastro falhou! Verifique os dados e tente novamente.");
        }
        return valido;
    }
}
