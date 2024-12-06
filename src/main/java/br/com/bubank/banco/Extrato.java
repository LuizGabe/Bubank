package br.com.bubank.banco;

import java.util.LinkedList;

public class Extrato {
    private final LinkedList<Transacao> transacoes = new LinkedList<>();

    public Extrato() {
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void imprimir() {
        for (Transacao transacao : transacoes) {
            System.out.println(transacao.getTransacao());
        }
    }
}
