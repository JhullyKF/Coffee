package com.github.coffe.persistencia;

import com.github.coffe.model.servicos.Produto;

public class ProdutoPersistencia extends PersistenciaDados<Produto>{
    public ProdutoPersistencia(String caminho) {
        super(caminho);
    }

    @Override
    protected Produto fromString(String texto) {
        return Produto.fromString(texto);
    }
}
