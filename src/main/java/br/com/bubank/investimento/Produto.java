package br.com.bubank.investimento;

public class Produto {
    private String nome;
    private double taxa;
    private double saldo;
    private double valorMinimo;
    
    public Produto(String nome, double taxa, double valorMinimo) {
        this.nome = nome;
        this.taxa = taxa;
        this.saldo = 0;
        this.valorMinimo = valorMinimo;
    }
    
    
//    GETTERS E SETTERS
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getTaxa() {
        return this.taxa;
    }
    
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public double getValorMinimo() {
        return this.valorMinimo;
    }
    
    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }
}
