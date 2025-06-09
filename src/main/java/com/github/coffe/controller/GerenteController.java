package com.github.coffe.controller;

import com.github.coffe.model.entidades.Funcionario;
import com.github.coffe.model.entidades.Gerente;
import com.github.coffe.model.entidades.Vendedor;
import com.github.coffe.utils.Validador;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GerenteController {
    private static final Logger log = LogManager.getLogger(GerenteController.class);
    private final FuncionarioController fc;
    private Gerente gerente;
    private final Validador validador = new Validador();

    public GerenteController(){
        fc = new FuncionarioController();
    }
    public void getTotalVendas(){
        PedidoController pc = new PedidoController();
        gerente.setTotalVendas(pc.getPedidosValidados().size());
    }
    public void loginGerente(Gerente gerente){
        this.gerente = gerente;
    }

    public boolean cadastrarVendedor(Vendedor v){
        if(validador.validarCPF(v.getCpf()) && validador.validarEmail(v.getEmail()) &&
                !validador.cpfExistente(fc.getFuncionarios(), v.getCpf()) &&
                !validador.emailExistente(fc.getFuncionarios(), v.getEmail())) {
            fc.getFuncionarios().add(v);
            log.info("Gerente: {} cadastrou novo funcionario vendedor {}", gerente.getNome(), v.getIdFuncionario());
            return true;
        }
        log.warn("Erro ao cadastrar novo vendedor. Dados inválidos ou já existentes");
        return false;
    }

    public boolean demitirFuncionario(Funcionario f){
        fc.getFuncionarios().remove(f);

        if(!fc.getFuncionarios().contains(f)) {
            log.info("Gerente {} demitiu funcionario {} com sucesso", gerente.getNome(), f.getIdFuncionario());
            return true;
        }
        log.warn("Erro ao demitir funcionario {} ID : {}", f.getNome(), f.getIdFuncionario());
        return false;
    }

    public boolean alterarSalario(Funcionario f, double salario){
        try{
            f.setSalario(salario);
            log.info("Gerente {} alterou salário de funcionario {}", gerente.getNome(), f.getIdFuncionario());
            if (fc.getFuncionarios().contains(f)) return true;
        } catch (IllegalArgumentException e) {
            log.warn("Erro ao alterar salário do funcionário {}: {}", f.getIdFuncionario(), e);
        }
        return false;
    }
}
