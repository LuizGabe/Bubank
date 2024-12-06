package br.com.bubank.banco;

import br.com.bubank.model.Usuario;

public class Conta {
    private final Usuario usuario;
    private final String numeroConta;
    private String agencia;

    public Conta(Usuario usuario) {
        this.usuario = usuario;
        this.numeroConta = String.valueOf((int) (Math.random() * 10000));
        this.agencia = "0001";
    }

    public String getNumeroConta() { return this.numeroConta; }

    public String getAgencia() { return this.agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public Usuario getUsuario() { return this.usuario; }

}


