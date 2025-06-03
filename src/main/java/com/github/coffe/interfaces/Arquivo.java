package com.github.coffe.interfaces;

import java.util.ArrayList;

public interface Arquivo<T>{
    void salvarEmArquivo(ArrayList<T> list);
    ArrayList<T> carregarDoArquivo();
}
