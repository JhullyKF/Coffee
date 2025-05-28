package com.github.coffe.model.entidades;

public class FuncionarioFactory {
    public static Funcionario fromString(String linha) {
        String[] partes = linha.split(", ");

        String tipo = partes[0].trim();

        return switch (tipo) {
            case "Gerente" -> Gerente.fromString(linha);
            case "Vendedor" -> Vendedor.fromString(linha);
            default -> throw new IllegalArgumentException("Tipo de funcionário desconhecido: " + tipo);
        };
    }
}
