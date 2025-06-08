package com.github.coffe.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProximoIdManager {

    public static final int cliente = 0;
    public static final int funcionario = 1;
    public static final int produto = 2;
    public static final int pedido = 3;
    private static List<Integer> ids = new ArrayList<>();
    
    static {
        carregarIds();
    }

    private static void carregarIds() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/github/coffe/dados/proximo_id.txt"))) {
            String linha;
            ids.clear();
            while ((linha = br.readLine()) != null) {
                ids.add(Integer.parseInt(linha.trim()));
            }
        } catch (IOException | NumberFormatException e) {
            for (int i = 0; i < 4; i++) ids.add(1);
            salvarIds();
        }
    }

    private static void salvarIds() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/com/github/coffe/dados/proximo_id.txt"))) {
            for (Integer id : ids) {
                bw.write(String.valueOf(id));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getProximoId(int tipo) {
        int idAtual = ids.get(tipo);
        ids.set(tipo, idAtual + 1);
        salvarIds();
        return idAtual;
    }
}
