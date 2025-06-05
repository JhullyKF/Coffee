package com.github.coffe.view;

import com.github.coffe.controller.PedidoController;
import com.github.coffe.controller.VendedorController;
import com.github.coffe.model.servicos.Pedido;

import java.util.Scanner;

public class VendedorView {
    private final Scanner sc = new Scanner(System.in);
    private PedidoController pedidoController = new PedidoController();
    private VendedorController vendedorController = new VendedorController();

    public void menuVendedor(){
        int op;
        while(true){
            System.out.println("======  Selecione uma opção:  =======");
            System.out.println("[1] - Listar pedidos");
            System.out.println("[2] - Processar pedidos");
            System.out.println("[3] - Editar pedidos");
            System.out.println("[0] - voltar");                 //ok
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 0: return;

                case 1: listarPedidos(); break;

                case 2: processarPedido(); break;

                case 3:
                    break;

                    default:
                    System.out.println("Entrada invalida! Tente novamente");
                    break;
            }
        }
    }

    public void listarPedidos(){
        if (pedidoController.getPedidos().isEmpty()){
            System.out.println("Lista de pedidos vazia");
            return;
        }

        for (Pedido p: pedidoController.getPedidos()){
            p.exibirDados();
        }
    }

    public void processarPedido(){
        if (pedidoController.getPedidos().isEmpty()){
            System.out.println("Lista de pedidos vazia");
            return;
        }
        for(Pedido p: pedidoController.getPendentes()){
            p.exibirDados();
        }

        System.out.println("Informe o id do pedido: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean result = vendedorController.processarPedido(id);
    }
}
