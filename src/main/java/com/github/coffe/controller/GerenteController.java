package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Gerente;
import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.utils.Validador;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GerenteController {
    private static final Logger log = LogManager.getLogger(GerenteController.class);
    private final FuncionarioController fc = new FuncionarioController();
    private Gerente gerente;
    private final Validador validador = new Validador();


    public void loginGerente(Funcionario gerente){
        this.gerente = (Gerente) gerente;
        this.gerente.setTotalVendas(totalVendas());
    }

    public int totalVendas(){
        int qtdVendas=0;
        for(Funcionario f: fc.getFuncionarios()) {
            if (f instanceof Vendedor){
                qtdVendas += ((Vendedor) f).getTotalVendas();
            }
        }
        return qtdVendas;
    }

    public boolean cadastrarVendedor(Vendedor v){
        if(validador.validarCPF(v.getCpf()) && validador.validarEmail(v.getEmail()) &&
                !validador.cpfExistente(fc.getFuncionarios(), v.getCpf()) &&
                !validador.emailExistente(fc.getFuncionarios(), v.getEmail())) {

            fc.getFuncionarios().add(v);
            log.info("Gerente: {} cadastrou novo funcionario vendedor {}", gerente.getNome(), v.getIdFuncionario());
            fc.getFuncionarioPersistencia().salvarEmArquivo(fc.getFuncionarios());
            return true;
        }
        log.warn("Erro ao cadastrar novo vendedor. Dados inválidos ou já existentes");
        return false;
    }

    public boolean demitirFuncionario(Vendedor v){
        fc.getFuncionarios().remove(v);
        fc.getFuncionarioPersistencia().salvarEmArquivo(fc.getFuncionarios());
        if(!fc.getFuncionarios().contains(v)) {
            log.info("Gerente {} demitiu funcionario {} com sucesso", gerente.getNome(), v.getIdFuncionario());
            fc.atualizarListaFuncionarios();
            return true;
        }
        log.warn("Erro ao demitir funcionario {} ID : {}", v.getNome(), v.getIdFuncionario());
        return false;
    }

    public boolean alterarSalario(Funcionario f, double salario){
        try{
            f.setSalarioFixo(salario);
            fc.getFuncionarioPersistencia().salvarEmArquivo(fc.getFuncionarios());
            log.info("Gerente {} alterou salário de funcionario {}", gerente.getNome(), f.getIdFuncionario());
            if (fc.getFuncionarios().contains(f)) return true;
        } catch (IllegalArgumentException e) {
            log.warn("Erro ao alterar salário do funcionário {}: {}", f.getIdFuncionario(), e);
        }
        return false;
    }
}
