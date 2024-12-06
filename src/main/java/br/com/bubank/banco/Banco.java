package br.com.bubank.banco;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    // Atributos
    private String nome;
    private final List<Conta> contas;
    private int qtdContas;

    // Construtor
    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
        this.qtdContas = 0;
    }

    // Métodos
    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
        this.qtdContas = this.qtdContas + 1;
    }

    public void removerConta(Conta conta) {
        this.contas.remove(conta);
        this.qtdContas = this.qtdContas - 1;
    }

    public Conta buscarConta(String numero, String agencia) {
        for (Conta conta : this.contas) {
            if (conta.getNumeroConta().equals(numero) && conta.getAgencia().equals(agencia)) {
                return conta;
            }
        }

        return null;
    }

    public List<Conta> listarContas() {
        return this.contas;
    }

    public int getQtdContas() {
        return this.qtdContas;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

