package com.github.coffe.persistencia;
import com.github.coffe.interfaces.Arquivo;

import java.io.*;
import java.util.ArrayList;

public abstract class PersistenciaDados<T> implements Arquivo<T> {

    private final File arquivo;

    public PersistenciaDados(String caminho){
        this.arquivo = new File(caminho);
    }

    @Override
    public void salvarEmArquivo(ArrayList<T> list) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))){
            for(T obj : list){
                bw.write(obj.toString());
                bw.newLine();
            }
        } catch (IOException e){
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<T> carregarDoArquivo() {
        ArrayList<T> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(this.arquivo))){
            String linha;
            while ((linha = br.readLine()) != null){
                list.add(fromString(linha));
            }
        } catch (IOException ignored){}
        return list;
    }

    protected abstract T fromString(String texto);
}
