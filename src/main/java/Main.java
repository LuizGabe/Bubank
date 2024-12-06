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

        Pessoa pessoa1 = new Pessoa("João", "123.456.789-00", new Date(), "joao@gmail.com", "1234-5678", "Rua 1", "Cidade 1", "Estado 1", "12345-678");
        Pessoa pessoa2 = new Pessoa("Maria", "987.654.321-00", new Date(), "Maria@gmail.com", "8765-4321", "Rua 2", "Cidade 2", "Estado 2", "87654-321");
        Pessoa pessoa3 = new Pessoa("José", "123.456.789-00", new Date(), "Jose@gmail.com", "1234-5678", "Rua 3", "Cidade 3", "Estado 3", "12345-678");

        // Criar Usuários
        Usuario usuarioJoao = new Usuario("123", "joao123", pessoa1);
        Usuario usuarioMaria = new Usuario("456", "maria456", pessoa2);
        Usuario usuarioJose = new Usuario("789", "jose789", pessoa3);

        Conta contaJoao = new Conta(usuarioJoao);
        Conta contaMaria = new Conta(usuarioMaria);

        ContaCorrente contaCorrenteJoao = new ContaCorrente(contaJoao, usuarioJoao);
        ContaCorrente contaCorrenteMaria = new ContaCorrente(contaMaria, usuarioMaria);

        ContaInvestimento contaInvestimentoJoao = new ContaInvestimento(contaJoao, usuarioJoao);
        ContaInvestimento contaInvestimentoMaria = new ContaInvestimento(contaMaria, usuarioMaria);

        // Depositar
        contaCorrenteJoao.depositar(1000);
        contaCorrenteMaria.depositar(2000);

        contaInvestimentoJoao.depositar(1000);
        contaInvestimentoMaria.depositar(2000);

        // Sacar
        contaCorrenteJoao.sacar(500);
        contaCorrenteMaria.sacar(1000);

        contaInvestimentoJoao.sacar(500);
        contaInvestimentoMaria.sacar(1000);

        // Transferir
        contaCorrenteJoao.transferir(contaCorrenteMaria, 500);
        // contaInvestimento1.transferir(contaInvestimento2, 500); // POR ENQUANTO NÃO

        Produto produto1 = new Produto("CDB (Bubank)", 1.721, 1000);
        Produto produto2 = new Produto("CDI", 1.241, 1);
        Produto produto3 = new Produto("FII BuImoveis", 1.424, 500);
        Produto produto4 = new Produto("LCA BuAgro", 1.366, 100);
        Produto produto5 = new Produto("LCOCO BuEiro", 0.966, 0.1);
        
        
        List<Transacao> teste = contaCorrenteMaria.getExtrato();
        for (Transacao transacao : teste) {
            System.out.println(transacao.getTransacao());
        }
        
        


    }
}
