package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.model.servicos.ItemPedido;
import com.github.coffe.model.servicos.Pedido;
import com.github.coffe.model.servicos.Produto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VendedorController {
    private static final Logger log = LogManager.getLogger(VendedorController.class);
    private final PedidoController pedidoController;
    private final ProdutoController produtoController;
    private Vendedor vendedor;
    private final FuncionarioController fc;

    public VendedorController() {
        pedidoController = new PedidoController();
        produtoController = new ProdutoController();
        fc = new FuncionarioController();
    }

    public void loginVendedor(Funcionario vendedor){
        this.vendedor = (Vendedor) vendedor;
        log.info("Vendedor {} logado com sucesso", vendedor.getIdFuncionario());
    }

    public void exibirDados(){
        fc.atualizarListaFuncionarios();
        vendedor = (Vendedor) fc.buscaFuncionario(vendedor.getIdFuncionario());

        System.out.println("+----------------------+----------------------+");
        System.out.printf ("| %-20s | %-20s |\n", "Campo", "Valor");
        System.out.println("+----------------------+----------------------+");
        System.out.printf ("| %-20s | %-20s |\n", "ID", vendedor.getIdFuncionario());
        System.out.printf ("| %-20s | %-20s |\n", "Nome", vendedor.getNome());
        System.out.printf ("| %-20s | %-20s |\n", "Email", vendedor.getEmail());
        System.out.printf ("| %-20s | %-20s |\n", "CPF", vendedor.getCpf());
        System.out.printf ("| %-20s | %-20s |\n", "Senha", vendedor.getSenha());
        System.out.printf ("| %-20s | %-20d |\n", "Vendas", vendedor.getTotalVendas());
        System.out.println("+----------------------+----------------------+");
    }

    public boolean editarDados(int op, String campo){
        Vendedor v = (Vendedor) fc.buscaFuncionario(vendedor.getIdFuncionario());
        if (v == null){
            throw new IllegalArgumentException("Não foi possivel achar o funcionario na base de dados");
        }

        switch (op){
            case 1:
                v.setEmail(campo);
                break;

            case 2:
                v.setSenha(campo);
                break;

            default:
                System.out.println("Entrada inválida");
                break;
        }
        vendedor = v;
        fc.getFuncionarioPersistencia().salvarEmArquivo(fc.getFuncionarios());

        return fc.getFuncionarios().contains(vendedor);
    }

    public boolean processarPedido(int id) {
        if (vendedor == null) {
            log.warn("Erro ao logar o vendedor");
            return false;
        }
        Pedido pedido = pedidoController.getPedidoId(id);
        if (pedido == null){
            log.warn("Pedido não encontrado");
            return false;
        }

        for (ItemPedido i : pedido.getItens()){
            pedidoController.verificarProduto(i);
            Produto produto = produtoController.alterarEstoque(i.getIdProduto(), i.getQuantidade());

            if (produto != null) {
                pedido.setObservacao("Item " + i.getIdProduto() + " removido: quantidade excede estoque");
            }
        }

        pedido.setStatus("Processado");
        pedido.setIdVendedor(vendedor.getIdFuncionario());

        Vendedor v = (Vendedor) fc.buscaFuncionario(vendedor.getIdFuncionario());
        v.setTotalVendas(v.getTotalVendas() + 1);

        pedidoController.getPedidoPersistencia().salvarEmArquivo(pedidoController.getPedidos());
        pedidoController.carregarStatusPedidos();
        fc.getFuncionarioPersistencia().salvarEmArquivo(fc.getFuncionarios());
        if (pedido.getStatus().equals("Processado")){
            log.info("Pedido {} processado por vendedor {} com sucesso", pedido.getId_Pedido(), vendedor.getIdFuncionario());
            return true;
        }
        return false;
    }


}

