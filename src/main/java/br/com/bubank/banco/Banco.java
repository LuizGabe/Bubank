package br.com.bubank.banco;

import br.com.bubank.investimento.Produto;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    // Atributos
    private String nome;
    private final List<ContaCorrente> contasCorrente;
    private final List<ContaInvestimento> contasInvestimento;

    // Construtor
    public Banco(String nome) {
        this.nome = nome;
        this.contasCorrente = new ArrayList<>();
        this.contasInvestimento = new ArrayList<>();
    }

    // Métodos
    public void addContaCorrente(ContaCorrente contaCorrente) {
        this.contasCorrente.add(contaCorrente);
    }
    public void addContaInvestimento(ContaInvestimento contaInvestimento) {
        this.contasInvestimento.add(contaInvestimento);
    }
    
    public void removeContaCorrente(ContaCorrente contaCorrente) {
        this.contasCorrente.remove(contaCorrente);
    }
    public void removeContaInvestimento(ContaInvestimento contaInvestimento) {
        this.contasInvestimento.remove(contaInvestimento);
    }

    public ContaCorrente buscarContaCorrente(String numero, String agencia) {
        for (ContaCorrente contaCorrente : this.contasCorrente) {
            if (contaCorrente.getNumeroConta().equals(numero) && contaCorrente.getAgencia().equals(agencia)) {
                return contaCorrente;
            }
        }

        return null;
    }

    public List<ContaCorrente> listarContasCorrente() {
        return this.contasCorrente;
    }
    public List<ContaInvestimento> listarContasInvestimento() {
        return this.contasInvestimento;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Operações de Cc
    //Deposito:
    public void depositar(ContaCorrente origem, double saldo){
        
        // sem verificações
        double novoSaldo = origem.getSaldo() + saldo;
        origem.setSaldo(novoSaldo);
        
        Transacao transacao = new Transacao("Deposito", saldo, novoSaldo);
        origem.addTransacao(transacao);
    }
    
    // Depósito
    public void depositar(ContaInvestimento origem, double saldo){
        
        // sem verificações
        double novoSaldo = origem.getSaldo() + saldo;
        origem.setSaldo(novoSaldo);
        
        Transacao transacao = new Transacao("Deposito", saldo, novoSaldo);
        origem.addTransacao(transacao);
    }
    
    // Saque
    public void saque(ContaCorrente origem, double saldo){
        double saldoRestante = origem.getSaldo() - saldo;
        
        if (saldoRestante >= 0) {
            origem.setSaldo(origem.getSaldo() - saldo);
        
            Transacao transacao = new Transacao("Saque", saldo, saldoRestante);
            origem.addTransacao(transacao);
        } else {
            System.out.println("Pessoa " + origem.getUsuario().getNome() + "nao tem o valor suficiente para saque.");
        }
    }
    
    
    /*
    Transferencias: 
    Conta Corrente para Conta Corrente
    Conta Corrente para Conta Investimento
    Conta Investimento para Conta Corrente
    */
    public void transferencia(ContaCorrente origem, ContaCorrente destino, double valor){
        double saldoRestanteOrigem = origem.getSaldo() - valor;
        double novoSaldoDestino = destino.getSaldo() + valor;
        
        if (saldoRestanteOrigem >= 0) {
            
            origem.setSaldo(saldoRestanteOrigem);
            destino.setSaldo(novoSaldoDestino);
        
            Transacao origemTransacao = new Transacao("Transferencia realizada para "+destino.getUsuario().getNome(), valor, saldoRestanteOrigem);
            origemTransacao.setContaDestino(destino);
            
            Transacao destinoTransacao = new Transacao("Transferencia recebida de "+origem.getUsuario().getNome(), valor, novoSaldoDestino);
            destinoTransacao.setContaDestino(origem);
            
            origem.addTransacao(origemTransacao);
            destino.addTransacao(destinoTransacao);
        }
    }
    
    public void transferencia(ContaCorrente origem, ContaInvestimento destino, double valor) {
        double saldoRestanteOrigem = origem.getSaldo() - valor;
        double novoSaldoDestino = destino.getSaldo() + valor;
        
        if (saldoRestanteOrigem >= 0) {
            
            origem.setSaldo(saldoRestanteOrigem);
            destino.setSaldo(novoSaldoDestino);
        
            Transacao origemTransacao = new Transacao("Transferencia realizada para "+destino.getUsuario().getNome(), valor, saldoRestanteOrigem);
            origemTransacao.setContaDestino(destino);
            
            Transacao destinoTransacao = new Transacao("Transferencia recebida de "+origem.getUsuario().getNome(), valor, novoSaldoDestino);
            destinoTransacao.setContaDestino(origem);
            
            origem.addTransacao(origemTransacao);
            destino.addTransacao(destinoTransacao);
        }
    }
    
    public void transferencia(ContaInvestimento origem, ContaCorrente destino, double valor) {
        double saldoRestanteOrigem = origem.getSaldo() - valor;
        double novoSaldoDestino = destino.getSaldo() + valor;
        
        if (saldoRestanteOrigem >= 0) {
            
            origem.setSaldo(saldoRestanteOrigem);
            destino.setSaldo(novoSaldoDestino);
        
            Transacao origemTransacao = new Transacao("Transferencia realizada para "+destino.getUsuario().getNome(), valor, saldoRestanteOrigem);
            origemTransacao.setContaDestino(destino);
            
            Transacao destinoTransacao = new Transacao("Transferencia recebida de "+origem.getUsuario().getNome(), valor, novoSaldoDestino);
            destinoTransacao.setContaDestino(origem);
            
            origem.addTransacao(origemTransacao);
            destino.addTransacao(destinoTransacao);
        }
    }
    
    public void comprarProduto(ContaInvestimento contaInvestimento, Produto produto, double valor) {
        boolean comprou = false;
        double saldoContaInvestimento = contaInvestimento.getSaldo();
        
        for (Produto meuProduto : contaInvestimento.getProdutos()) {
            // \/ CODIGO SUGERIDO PELO EDITOR PARA meuProduto.getNome() == produto.getNome()
            if (meuProduto.getNome() == null ? produto.getNome() == null : meuProduto.getNome().equals(produto.getNome())) {
                
                // Verificar se tem saldo suficiente
                if (saldoContaInvestimento >= valor) {
                    
                    // Verifica se tem o valor mínimo para comprar
                    if (valor >= meuProduto.getValorMinimo()) {
                        
                        // Debita o saldo necessário
                        double novoSaldo = saldoContaInvestimento - valor;
                        contaInvestimento.setSaldo(novoSaldo);
                        
                        // Compra
                        meuProduto.setSaldo(meuProduto.getSaldo() + valor);
                       
                        // Gerar extrato
                        Transacao transacao = new Transacao("Compra", valor, novoSaldo);
                        transacao.setProduto(produto);
                        
                        contaInvestimento.addTransacao(transacao);
                        
                        System.out.println("Pessoa " + contaInvestimento.getUsuario().getNome() + " comprou R$" + valor + " do produto " + meuProduto.getNome()) ;
                        comprou = true;
                    } else {
                        System.out.println("Pessoa " + contaInvestimento.getUsuario().getNome() + " nao possui valor minimo de R$" + meuProduto.getValorMinimo() + " para comprar " + produto.getNome());
                    }
                } else {
                    System.out.println("Pessoa " + contaInvestimento.getUsuario().getNome() + " nao possui valor suficiente para comprar " + produto.getNome() + " (valor " + valor + ")");
                }
               
                // Parar depois que encontrou uma correspondencia
                break;
            }
        }
        
        // Se ele ainda não tiver o produto comprar pela primeira vez aqui
        if (comprou == false) {
            // Verificar se tem saldo suficiente
            if (saldoContaInvestimento >= valor) {
                    
                 // Verifica se tem o valor mínimo para comprar
                if (valor >= produto.getValorMinimo()) {
                        
                    // Debita o saldo necessário
                    double novoSaldo = saldoContaInvestimento - valor;
                    contaInvestimento.setSaldo(novoSaldo);
                    
                    // Compra
                    produto.setSaldo(valor);
                    contaInvestimento.addProduto(produto);
                    
                    // Gerar extrato
                    Transacao transacao = new Transacao("Compra", valor, novoSaldo);
                    transacao.setProduto(produto);
                        
                    contaInvestimento.addTransacao(transacao);
                    
                    System.out.println("Pessoa " + contaInvestimento.getUsuario().getNome() + " comprou R$" + valor + " do produto " + produto.getNome()) ;
                } else {
                    System.out.println("Pessoa " + contaInvestimento.getUsuario().getNome() + " nao possui valor minimo de R$" + produto.getValorMinimo() + " para comprar " + produto.getNome());
                }
            } else {
                System.out.println("Pessoa " + contaInvestimento.getUsuario().getNome() + " nao possui valor suficiente para comprar " + produto.getNome() + " (valor " + valor + ")");
            }
        }
       
    }
    
    public void venderProduto(ContaInvestimento contaInvestimento, Produto produto, double valor) {
        
        for (Produto meuProduto : contaInvestimento.getProdutos()) {
            
            // Acha o produto em meio a lista \/ CODIGO SUGERIDO PELO EDITOR PARA meuProduto.getNome() == produto.getNome()
            if (produto.getNome().equals(meuProduto.getNome())) {
                
                // Verificar se tem o suficiente para vender
                if (meuProduto.getSaldo() >= valor) {
                    
                    // Vender
                    meuProduto.setSaldo(meuProduto.getSaldo() - valor);
                   
                    // Creditar o saldo do produto na conta
                    double novoSaldo = contaInvestimento.getSaldo() + valor;
                    contaInvestimento.setSaldo(novoSaldo);
                   
                    // Gerar extrato
                    Transacao transacao = new Transacao("Venda", valor, novoSaldo);
                    transacao.setProduto(produto);
                        
                    contaInvestimento.addTransacao(transacao);
                    
                    System.out.println("Pessoa " + contaInvestimento.getUsuario().getNome() + " vendeu R$" + valor + " do produto " + meuProduto.getNome()) ;
                } else {
                    System.out.println(contaInvestimento.getUsuario().getNome() + " nao possui valor minimo de " + produto.getNome() + " para vender.");
                }
            // Parar depois que encontrou uma correspondencia
            break;
            }    
        }       
    }

    public void rodarInvestimentos() {
        for(ContaInvestimento contasInvestimento : this.contasInvestimento) {
            for(Produto produto : contasInvestimento.getProdutos()) {
                double novoValorProduto = produto.getSaldo() * produto.getTaxa();
                
                System.out.println("Produto "+produto.getNome()+" de "+contasInvestimento.getUsuario().getNome()+" foi de R$"+produto.getSaldo()+" para R$"+novoValorProduto);
                produto.setSaldo(novoValorProduto);
            }
        }
    }
    
}

