package br.com.bubank.banco;

import br.com.bubank.model.Usuario;
import br.com.bubank.investimento.Produto;
import java.util.ArrayList;
import java.util.List;

public class ContaInvestimento extends Conta {
    private double saldo;
    private final Usuario usuario;
    private final List<Transacao> extrato;
    private final List<Produto> produtos;

    public ContaInvestimento(Usuario usuario) {
        super(usuario);
        this.saldo = 0;
        this.usuario = usuario;
        this.extrato = new ArrayList<>(); 
        this.produtos = new ArrayList<>();
    }

    public double getSaldo() { return this.saldo; }
    public void setSaldo(double saldo) { 
        this.saldo = saldo;
    }

    public List<Transacao> getExtrato() { return this.extrato; }
    public void addTransacao(Transacao transacao){
        this.extrato.add(transacao);
    }
    
    public List<Produto> getProdutos() { return this.produtos; }
    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }
    public void removeProduto(Produto produto) {
        
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("###");
        System.out.println("");
        System.out.println("Detalhes da Conta Investimento de " + this.usuario.getNome() + ":");
        System.out.println("    Saldo Atual: R$" + this.getSaldo());
        System.out.println("    Numero da conta: " + this.getNumeroConta() + " - " + this.getAgencia());
        if (!this.produtos.isEmpty()) {
            System.out.println("    Meus Produtos:");
            double totalInvestido = 0;
            for(Produto produto : this.produtos) {
                System.out.println("        "+produto.getDetalhes());
                totalInvestido += produto.getSaldo();
            }
            System.out.println("    Total Investido: R$"+totalInvestido);
        } else {
            System.out.println("    Meus Produtos: Voce nao tem produtos comprados ainda.");
        }
        
        
        System.out.println("    Extrato: ");
        for (Transacao transacao : this.extrato) {
            System.out.println("        " + transacao.getTransacao());
        }
        
        System.out.println("");
        System.out.println("###");
    }

}

