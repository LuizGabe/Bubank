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
    
    public ContaCorrente getContaCorrenteVinculada() {
        return destinoCorrente;
    }
    public ContaInvestimento getContaInvestimentoVinculada() {
        return destinoInvestimento;
    }
    public Produto getProdutoVinculado() {
        return produto;
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
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDetalheVinculado() {
        String texto = "";
        if (produto != null && produto.getNome() != null && !produto.getNome().isEmpty()) {
            texto += "Produto: " + produto.getNome();
        } else if (destinoCorrente != null && destinoCorrente.getUsuario().getNome() != null) {
            texto += "Conta Corrente: " + destinoCorrente.getUsuario().getNome();
        } else if (destinoInvestimento != null && destinoInvestimento.getUsuario().getNome() != null) {
            texto += "Conta Investimento: " + destinoInvestimento.getUsuario().getNome();
        }
        
        return texto;
    }

    public String getTransacao() {
        String texto = tipo + " - R$ " + valor + " - " + data;
        
        if (descricao == null) {
            texto += " - " + descricao;
        }
        String detalhe = getDetalheVinculado();
        if (!"".equals(detalhe)) {
            texto += " - " + detalhe;
        }

        return texto;
    }
}
