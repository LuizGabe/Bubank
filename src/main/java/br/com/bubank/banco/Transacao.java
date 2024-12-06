package br.com.bubank.banco;

import br.com.bubank.investimento.Produto;

import java.util.Date;

public class Transacao {

    private final String tipo;
    private final double valor;
    private final double saldoRestante;
    private final Date data;
    private String descricao;
    private Produto produto;
    private ContaCorrente destinoCorrente;
    private ContaInvestimento destinoInvestimento;

    public Transacao(String tipo, double valor, double saldoRestante) {
        this.tipo = tipo;
        this.valor = valor;
        this.saldoRestante = saldoRestante;
        this.data = new Date();
        this.descricao = "";
    }

    public void setContaDestino(ContaCorrente destino) {
        this.destinoCorrente = destino;
    }

    public void setContaDestino(ContaInvestimento destino) {
        this.destinoInvestimento = destino;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTransacao() {
        boolean isProduto = !this.produto.getNome().isEmpty();
        boolean isContaCorrente = this.destinoCorrente.getConta().getUsuario().getNome().isBlank();
        boolean isContaInvestimento = this.destinoInvestimento.getConta().getUsuario().getNome().isBlank();

        if (isProduto) {
            return tipo + " - R$ " + valor + " - " + data + " - " + descricao + " - " + this.produto.getNome();
        }
        if (isContaCorrente) {
            return tipo + " - R$ " + valor + " - " + data + " - " + descricao + " - " + this.destinoCorrente.getConta();
        }
        if (isContaInvestimento) {
            return tipo + " - R$ " + valor + " - " + data + " - " + descricao + " - " + this.destinoInvestimento.getConta();
        }

        // Montando a string de transação com as informações escolhidas
        return tipo + " - R$ " + valor + " - " + data + " - " + descricao + " - ";
    }
    
    private void getTransacao() {
        String texto = tipo + " - R$ " + valor + " - " + data + " - " + descricao;
        
        boolean isProduto = this.produto.getNome().isEmpty();
        boolean isContaCorrente = this.destinoCorrente.getConta().getUsuario().getNome().isBlank();
        boolean isContaInvestimento = this.destinoInvestimento.getConta().getUsuario().getNome().isBlank();

        if (!isProduto) {
            texto += " - " + this.produto.getNome();
        }else if (isContaCorrente) {
            texto.concat(this.destinoInvestimento.getConta().toString());
        }else if (isContaInvestimento) {
            return tipo + " - R$ " + valor + " - " + data + " - " + descricao + " - " + this.destinoInvestimento.getConta();
        }

        // Montando a string de transação com as informações escolhidas
        return texto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
