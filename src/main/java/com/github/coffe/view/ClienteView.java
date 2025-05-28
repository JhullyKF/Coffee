package com.github.coffe.view;

import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.Scanner;

public class ClienteView {
    Scanner sc = new Scanner(System.in);

    public void loginCliente(){
        System.out.println("\nInforme seus dados:");
        System.out.println("\nCPF ou Email: ");
        String identificador = sc.nextLine();
        System.out.println("\nSenha: ");
        String senha = sc.nextLine();
    }

    public void autoCadastro(){
        System.out.println("\nDigite seu nome:");
        String nome  = sc.nextLine();
        System.out.println("Digite seu cpf:");
        String cpf = sc.nextLine();
        System.out.println("Digite seu email:");
        String email = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
    }
}
