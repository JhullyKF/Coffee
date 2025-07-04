package com.github.coffe.utils;

import com.github.coffe.interfaces.Identificavel;
import java.util.InputMismatchException;
import java.util.List;

public class Validador{

    public boolean validarCPF(String cpf) {
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) return false;

        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public <T extends Identificavel> boolean cpfExistente(List<T> lista, String cpf) {
        for (T item : lista) {
            if (item.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public <T extends Identificavel> boolean emailExistente(List<T> lista, String email) {
        for (T item : lista) {
            if (item.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
