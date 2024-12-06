package br.com.bubank.banco;

import br.com.bubank.model.Usuario;

import java.util.ArrayList;
import java.util.List;

// herdar de Conta
public class ContaCorrente extends Conta {
    private double saldo;
    private final Conta conta;
    private final List<Transacao> extrato;

    public ContaCorrente(Conta conta, Usuario usuario) {
        super(usuario);
        this.saldo = 0;
        this.conta = conta;
        this.extrato = new ArrayList<Transacao>();
    }

    public void depositar(double valor) {
        this.saldo += valor;
        Transacao transacao = new Transacao("deposito", valor, this.saldo);
        this.extrato.add(transacao);
    }

    public void sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            Transacao transacao = new Transacao("saque", valor, this.saldo);
            this.extrato.add(transacao);
        } // Colocar o tratamento de erros no lugar que for acessar o método, não aqui
    }

    public double getSaldo() { return this.saldo; }

    public List<Transacao> getExtrato() { return this.extrato; }

    public boolean transferir(ContaInvestimento destino, double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            destino.depositar(valor);

            Transacao transacao = new Transacao("transferencia", valor, this.saldo);
            transacao.setContaDestino(destino);
            
            this.extrato.add(transacao);
            return true;
        }

        return false;
    }

    public boolean transferir(ContaCorrente destino, double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            destino.depositar(valor); // DEVE VALIDAR SE A CONTA EXISTE ANTES DE DEPOSITAR

            Transacao transacao = new Transacao("transferencia", valor, this.saldo);
            transacao.setContaDestino(destino);
            transacao.setDescricao("Transferência para " + destino.getConta().getUsuario().getNome());
            
            this.extrato.add(transacao);
            return true;
        }

        return false;
    }

    public Conta getConta() { return conta; }
}