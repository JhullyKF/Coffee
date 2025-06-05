package com.github.coffe.persistencia;

import com.github.coffe.model.servicos.Pedido;

public class PedidoPersistencia extends PersistenciaDados<Pedido>{
    public PedidoPersistencia(String caminho) {
        super(caminho);
    }

    @Override
    protected Pedido fromString(String texto) {
        return Pedido.fromString(texto);
    }
}
