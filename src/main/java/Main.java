import br.com.bubank.banco.Banco;
import br.com.bubank.banco.Conta;
import br.com.bubank.banco.ContaCorrente;
import br.com.bubank.banco.ContaInvestimento;
import br.com.bubank.banco.Transacao;
import br.com.bubank.investimento.Produto;
import br.com.bubank.model.Pessoa;
import br.com.bubank.model.Usuario;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criar Pessoas
        Banco bubank = new Banco("Bubank");

        Pessoa pessoa1 = new Pessoa("Joao", "123.456.789-00", new Date(), "joao@gmail.com", "1234-5678", "Rua 1", "Cidade 1", "Estado 1", "12345-678");
        Pessoa pessoa2 = new Pessoa("Maria", "987.654.321-00", new Date(), "Maria@gmail.com", "8765-4321", "Rua 2", "Cidade 2", "Estado 2", "87654-321");
        Pessoa pessoa3 = new Pessoa("Jose", "123.456.789-00", new Date(), "Jose@gmail.com", "1234-5678", "Rua 3", "Cidade 3", "Estado 3", "12345-678");

        // Criar Usuários
        Usuario usuarioJoao = new Usuario("123", "joao123", pessoa1);
        Usuario usuarioMaria = new Usuario("456", "maria456", pessoa2);
        Usuario usuarioJose = new Usuario("789", "jose789", pessoa3);
        
        // Criar contas correntes
        ContaCorrente contaCorrenteJoao = new ContaCorrente(usuarioJoao);
        ContaCorrente contaCorrenteMaria = new ContaCorrente(usuarioMaria);
        
        bubank.addContaCorrente(contaCorrenteJoao);
        bubank.addContaCorrente(contaCorrenteMaria);
        
        // Criar contas investimento
        ContaInvestimento contaInvestimentoJoao = new ContaInvestimento(usuarioJoao);
        ContaInvestimento contaInvestimentoMaria = new ContaInvestimento(usuarioMaria);

        bubank.addContaInvestimento(contaInvestimentoJoao);
        bubank.addContaInvestimento(contaInvestimentoMaria);
        
        // Depositar
        bubank.depositar(contaCorrenteJoao, 10000);
        bubank.depositar(contaCorrenteMaria, 20000);

        bubank.transferencia(contaCorrenteJoao, contaInvestimentoJoao, 7500);
        bubank.transferencia(contaCorrenteMaria, contaInvestimentoMaria, 15000);

        // Sacar
        bubank.saque(contaCorrenteJoao, 500);
        bubank.saque(contaCorrenteMaria, 1000);

        // Transferir
        bubank.transferencia(contaCorrenteJoao, contaCorrenteMaria, 500);
        
        Produto produto1 = new Produto("CDB (Bubank)", 1.721, 1000);
        Produto produto2 = new Produto("CDI", 1.241, 1);
        Produto produto3 = new Produto("FII BuImoveis", 1.424, 500);
        Produto produto4 = new Produto("LCA BuAgro", 1.366, 100);
        Produto produto5 = new Produto("LCI", 1.400, 100);
        
        contaInvestimentoJoao.exibirDetalhes();
        
        bubank.comprarProduto(contaInvestimentoJoao, produto1, 2000);
        bubank.comprarProduto(contaInvestimentoJoao, produto2, 1000);
        bubank.comprarProduto(contaInvestimentoJoao, produto3, 1000);
        bubank.comprarProduto(contaInvestimentoJoao, produto4, 1000);
        bubank.comprarProduto(contaInvestimentoJoao, produto5, 1000);
        bubank.comprarProduto(contaInvestimentoJoao, produto1, 1500);
        
        bubank.venderProduto(contaInvestimentoJoao, produto2, 500);
        bubank.venderProduto(contaInvestimentoJoao, produto4, 700);
        
        contaInvestimentoJoao.exibirDetalhes();
        
        // executar ações - MEUS TESTES MALUCOS
        bubank.rodarInvestimentos();
        
        contaInvestimentoJoao.exibirDetalhes();
        
        /*
            BUSCA POR INFORMACOES DE UMA TRANSACAO FEITA.
        
        for(Transacao transacao : contaCorrenteJoao.getExtrato()) {
            System.out.println("transacao.getContaInvestimentoVinculada()"+transacao.getContaInvestimentoVinculada());
            if (transacao.getContaInvestimentoVinculada() != null) {
                System.out.println("Destino "+transacao.getContaInvestimentoVinculada().getNumeroConta());
                System.out.println("Destino "+transacao.getValor());
            }
        } */
    }
}
