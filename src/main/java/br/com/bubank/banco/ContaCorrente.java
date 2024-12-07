package br.com.bubank.banco;

import br.com.bubank.model.Usuario;

import java.util.ArrayList;
import java.util.List;

// herdar de Conta
public class ContaCorrente extends Conta {
    private double saldo;
    private final List<Transacao> extrato;
    private final Usuario usuario;

    public ContaCorrente(Usuario usuario) {
        super(usuario);
        this.saldo = 0;
        this.usuario = usuario;
        this.extrato = new ArrayList<Transacao>();
    }
    
    public double getSaldo() { return this.saldo; }
    
    public void setSaldo(double valor){
        this.saldo = valor;
    }

    public List<Transacao> getExtrato() { return this.extrato; }
    
    public void addTransacao(Transacao transacao){
        this.extrato.add(transacao);
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("###");
        System.out.println("");
        System.out.println("Detalhes da Conta Corrente de " + this.usuario.getNome() + ":");
        System.out.println("    Saldo Atual: R$" + this.getSaldo());
        System.out.println("    Numero da conta: " + this.getNumeroConta() + " - " + this.getAgencia());
        System.out.println("    Extrato: ");
        for (Transacao transacao : this.extrato) {
            System.out.println(transacao.getTransacao());
        }
        System.out.println("");
        System.out.println("###");
    }
}