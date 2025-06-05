package com.github.coffe.persistencia;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.FuncionarioFactory;

public class FuncionarioPersistencia extends PersistenciaDados<Funcionario>{
    public FuncionarioPersistencia(String caminho) {
        super(caminho);
    }

    @Override
    protected Funcionario fromString(String texto) {
        return FuncionarioFactory.fromString(texto);
    }
}
