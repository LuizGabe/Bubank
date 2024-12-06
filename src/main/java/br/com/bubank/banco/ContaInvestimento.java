package br.com.bubank.banco;

import br.com.bubank.model.Usuario;
import br.com.bubank.investimento.Produto;
import java.util.ArrayList;
import java.util.List;

public class ContaInvestimento extends Conta {
    private double saldo;
    private final Conta conta;
    private final List<Transacao> extrato;
    private final List<Produto> produtos;

    public ContaInvestimento(Conta conta, Usuario usuario) {
        super(usuario);
        this.saldo = 0;
        this.conta = conta;
        this.extrato = new ArrayList<>(); 
        this.produtos = new ArrayList<>();
    }

    public void depositar(double valor) {
        this.saldo += valor;
        Transacao transacao = new Transacao("deposito", valor, this.saldo);
        this.extrato.add(transacao);
    }

    public void sacar(double valor) { // NÃO É SACAR, É PARA RESGATAR O DINHEIRO PARA A CONTA CORRENTE
        // ALEM DEVE TER O METODO PRA COMPRAR AÇÕES
        if (this.saldo >= valor) {
            this.saldo -= valor;
            Transacao transacao = new Transacao("saque", valor, this.saldo);
            this.extrato.add(transacao);
        } // Colocar o tratamento de erros no lugar que for acessar o método, não aqui
    }

    public double getSaldo() { return this.saldo; }

    public List<Transacao> getExtrato() { return this.extrato; }

    public void transferir(ContaCorrente destino, double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            destino.depositar(valor);

            Transacao transacao = new Transacao("transferencia", valor, this.saldo);
            transacao.setContaDestino(destino);
            
            this.extrato.add(transacao);
        } // Colocar o tratamento de erros no lugar que for acessar o método, não aqui
    }
    // SERA QUE DEVE SER POSSIVEL TRANSFERIR DE UMA CONTA INVESTIMENTO PARA OUTRA CONTA INVESTIMENTO?

    public Conta getConta() { return conta; }
    
    public void comprarProduto(Produto produto, double valor) {
        // PASSAR POR TODOS, SE JÁ TIVER, ACRESCENTAR NO SALDO O VALOR E GERAR UMA TRANSAÇÃO E COLOCAR EM EXTRATO
        // Se não tiver adicionar o produto com o saldo com o valor informado para comprar
        // Lembrar de debitar o valor comprado de ação da conta de ação do usuário
        
        boolean comprou = false;
        for (Produto meuProduto : produtos) {
            if (meuProduto.getNome() == produto.getNome()) {
                
                // Verificar se tem saldo suficiente
                if (this.saldo >= valor) {
                    
                    // Verifica se tem o valor mínimo para comprar
                    if (valor >= meuProduto.getValorMinimo()) {
                        
                        // Debita o saldo necessário
                        this.saldo = this.saldo - valor;
                        
                        // Compra
                        meuProduto.setSaldo(meuProduto.getSaldo() + valor);
                       
                        // Gerar extrato
                        Transacao transacao = new Transacao("compra", valor, this.saldo);
                        transacao.setProduto(produto);
                        
                        extrato.add(transacao);
                        comprou = true;
                    } else {
                        System.out.println("Pessoa " + this.conta.getUsuario().getNome() + "não possui valor mínimo de R$" + meuProduto.getValorMinimo() + "para comprar " + produto.getNome());
                    }
                } else {
                    System.out.println("Pessoa " + this.conta.getUsuario().getNome() + "não possui valor suficiente para comprar " + produto.getNome() + "(valor " + valor + ")");
                }
               
                // Parar depois que encontrou uma correspondencia
                break;
            }   
        }
        
        // Se ele ainda não tiver o produto comprar pela primeira vez aqui
        if (comprou == false) {
            // Verificar se tem saldo suficiente
            if (this.saldo >= valor) {
                    
                 // Verifica se tem o valor mínimo para comprar
                if (valor >= produto.getValorMinimo()) {
                        
                    // Debita o saldo necessário
                    this.saldo = this.saldo - valor;
                    
                    // Compra
                    produto.setSaldo(valor);
                    produtos.add(produto);
                    
                    // Gerar extrato
                    Transacao transacao = new Transacao("compra", valor, this.saldo);
                    transacao.setProduto(produto);
                        
                    extrato.add(transacao);
                } else {
                    System.out.println("Pessoa " + this.conta.getUsuario().getNome() + "não possui valor mínimo de R$" + produto.getValorMinimo() + "para comprar " + produto.getNome());
                }
            } else {
                System.out.println("Pessoa " + this.conta.getUsuario().getNome() + "não possui valor suficiente para comprar " + produto.getNome() + "(valor " + valor + ")");
            }
        }
    }
    
    public void venderProduto(Produto produto, double valor) {
        
        for (Produto meuProduto : produtos) {
            
            // Acha o produto em meio a lista
            if (meuProduto.getNome() == produto.getNome()) {
      
                // Verificar se tem o suficiente para vender
                if (meuProduto.getSaldo() >= valor) {
                    
                    // Vender
                    meuProduto.setSaldo(meuProduto.getSaldo() - valor);
                   
                    // Creditar o saldo do produto na conta
                    this.saldo = this.saldo + valor;
                   
                    // Gerar extrato
                    Transacao transacao = new Transacao("venda", valor, this.saldo);
                    transacao.setProduto(produto);
                        
                    extrato.add(transacao);    
                    } else {
                        System.out.println("Pessoa " + this.conta.getUsuario().getNome() + "não possui valor mínimo de R$" + meuProduto.getValorMinimo() + "para comprar " + produto.getNome());
                    }
                } else {
                    System.out.println("Pessoa " + this.conta.getUsuario().getNome() + "não possui valor suficiente para comprar " + produto.getNome() + "(valor " + valor + ")");
                }
               
                // Parar depois que encontrou uma correspondencia
                break;
            }       
        }
    }
