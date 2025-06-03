package com.github.coffe.persistencia;

import com.github.coffe.model.entidades.Cliente;

public class ClientePersistencia extends PersistenciaDados<Cliente> {

    public ClientePersistencia(String caminho) {
        super(caminho);
    }

    @Override
    protected Cliente fromString(String texto) {
        return Cliente.fromString(texto);
    }
}

