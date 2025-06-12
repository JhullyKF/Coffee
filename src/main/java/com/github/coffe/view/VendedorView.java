package com.github.coffe.view;

import com.github.coffe.controller.PedidoController;
import com.github.coffe.controller.VendedorController;

import com.github.coffe.model.servicos.Pedido;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class VendedorView {
    private static final Logger log = LogManager.getLogger(VendedorView.class);
    private final Scanner sc = new Scanner(System.in);
    private final PedidoController pedidoController = new PedidoController();
    private final VendedorController vc;
    private final PedidoView pedidoView = new PedidoView();

    public VendedorView(VendedorController vc) {
        this.vc = vc;
    }

    public void menuVendedor(){
        int op;
        while(true){
            System.out.println("======  Selecione uma opção:  =======");
            System.out.println("[1] - Listar pedidos");
            System.out.println("[2] - Processar pedidos");
            System.out.println("[3] - Minha conta");
            System.out.println("[0] - voltar");                 //ok
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 0: return;

                case 1: pedidoView.mostrarTodosPedidos(); break;

                case 2: processarPedido(); break;

                case 3: menuConta(); break;

                default:
                System.out.println("Entrada invalida! Tente novamente");
                break;
            }
        }
    }

    public void processarPedido(){
        for (int i = 0; i < 3; i++) {
            System.out.println(".");
            try {
                sleep(200);
            } catch (InterruptedException e) {
                log.error("e: ", e);
            }
        }
        pedidoController.carregarStatusPedidos();
        if (pedidoController.getPedidosPendentes().isEmpty()){
            System.out.println("Lista de pedidos vazia");
            return;
        }
        for(Pedido p: pedidoController.getPedidosPendentes()){
            p.exibirDados();
        }

        System.out.println("\n\nInforme o id do pedido: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean result = vc.processarPedido(id);
        if (result){
            System.out.println("Pedido processado com sucesso");
            return;
        }
        System.out.println("Erro ao processar pedido!");
    }

    public void menuConta() {
        int op;
        while (true) {
            for (int i = 0; i < 3; i++) {
                System.out.println(".");
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    log.error("e: ", e);
                }
            }

            System.out.println("======  Selecione uma opção:  =======");
            System.out.println("[1] - Listar meus dados");
            System.out.println("[2] - Editar meus dados");
            System.out.println("[0] - Voltar");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 0:
                    return;

                case 1:
                    vc.exibirDados();
                    break;

                case 2:

                    boolean result;
                    System.out.println("Selecione uma opção para editar: ");
                    System.out.println("[0] - Voltar");
                    System.out.println("[1] - Editar email");
                    System.out.println("[2] - Editar senha");
                    int editar = Integer.parseInt(sc.nextLine());

                    switch (editar) {
                        case 0:
                            return;

                        case 1:
                            System.out.println("Insira o novo email: ");
                            String email = sc.nextLine();
                            result = vc.editarDados(editar, email);
                            if (result){
                                System.out.println("Email editado com sucesso");
                                return;
                            }
                            break;

                        case 2:
                            System.out.println("Insira a nova senha: ");
                            String senha = sc.nextLine();
                            result = vc.editarDados(editar, senha);
                            if (result){
                                System.out.println("Senha editada com sucesso");
                                return;
                            }
                            break;

                        default:
                            System.out.println("Entrada inválida!"); break;
                    }
                    break;

                default:
                    System.out.println("Entrada inválida!"); break;
            }
        }
    }
}
