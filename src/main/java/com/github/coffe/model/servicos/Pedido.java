package com.github.coffe.model.servicos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    List<ItemPedido> itens = new ArrayList<ItemPedido>();
    private int id_Pedido;
    private int idCliente;
    private int idVendedor;
}
