package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.model.servicos.ItemPedido;
import com.github.coffe.model.servicos.Pedido;
import com.github.coffe.model.servicos.Produto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class VendedorController {
    private static final Logger log = LogManager.getLogger(VendedorController.class);
    private final PedidoController pedidoController;
    private final ProdutoController produtoController;
    private Vendedor vendedor;
    private final ArrayList<Pedido> totalVendas = new ArrayList<>();
    private final FuncionarioController fc;

    public VendedorController() {
        pedidoController = new PedidoController();
        produtoController = new ProdutoController();
        fc = new FuncionarioController();
    }

    public ArrayList<Pedido> getTotalVendas(){
        try {
            if (vendedor == null) {
                log.warn("Vendedor não carregado");
                throw new IllegalArgumentException("vendedor nulo.");
            } else {
                totalVendas.clear();

                for (Pedido p : pedidoController.getPedidos()) {
                    Integer idVendedor = p.getIdVendedor();
                    if (idVendedor != null && idVendedor.equals(vendedor.getIdFuncionario())) {
                        totalVendas.add(p);
                    }
                }

                log.info("Total de vendas do vendedor {} carregadas com sucesso", vendedor.getTotalVendas());
                return totalVendas;
            }
        } catch (Exception e) {
            log.warn("Erro ao carregar total de vendas do vendedor {}: {}",
                    (vendedor != null ? vendedor.getIdFuncionario() : "desconhecido"), e.getMessage());
        }
        return null;
    }

    public void loginVendedor(Funcionario vendedor){
        this.vendedor = (Vendedor) vendedor;
        if(getTotalVendas()!=null){
            this.vendedor.setTotalVendas(getTotalVendas().size());
        }
    }

    public void exibirDados(){
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
        Funcionario funcionario= null;
        for (Funcionario f: fc.getFuncionarios()){
            if (f.getIdFuncionario() == vendedor.getIdFuncionario()){
                funcionario = f;
            }
        }
        switch (op){
            case 1:
                funcionario.setEmail(campo);
                break;

            case 2:
                funcionario.setSenha(campo);
                break;

            default:
                System.out.println("Entrada inválida");
                break;
        }
        vendedor = (Vendedor) funcionario;
        fc.getFuncionarioPersistencia().salvarEmArquivo(fc.getFuncionarios());

        return fc.getFuncionarios().contains(vendedor);
    }

    public boolean processarPedido(int id) {
        Pedido pedido = null;

        for (Pedido p : pedidoController.getPendentes()) {
            if (p.getId_Pedido() == id) {
                pedido = p;
                log.info("Pedido encontrado");
                break;
            }
        }
        if (pedido == null) {
            log.warn("Pedido não encontrado");
            return false;
        }

        if (vendedor == null) {
            log.warn("Vendedor não está logado");
            return false;
        }

        try {
            for (ItemPedido i : pedido.getItens()) {
                Produto produto = produtoController.alterarEstoque(i.getIdProduto(), i.getQuantidade());

                if (produto != null) {
                    pedido.setObservacao("Item " + i.getIdProduto() + " removido: quantidade excede estoque");
                }
            }

            pedido.setStatus("Processado");
            pedido.setIdVendedor(vendedor.getIdFuncionario());
            pedidoController.getPedidoPersistencia().salvarEmArquivo(pedidoController.getPedidos());
            pedidoController.carregarStatusPedidos();
            log.info("Pedido {} processado por vendedor {} com sucesso", pedido.getId_Pedido(), vendedor.getIdFuncionario());
            return true;

        } catch (Exception e) {
            log.warn("Erro ao processar pedido: ", e);
        }

        return false;
    }
}

