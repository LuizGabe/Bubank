import br.com.bubank.banco.Conta;
import br.com.bubank.banco.ContaCorrente;
import br.com.bubank.banco.ContaInvestimento;
import br.com.bubank.banco.Transacao;
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
        Usuario usuario1 = new Usuario("123", "joao123", pessoa1);
        Usuario usuario2 = new Usuario("456", "maria456", pessoa2);
        Usuario usuario3 = new Usuario("789", "jose789", pessoa3);

        Conta conta1 = new Conta(usuario1);
        Conta conta2 = new Conta(usuario2);

        ContaCorrente contaCorrenteJoao = new ContaCorrente(conta1, usuario1);
        ContaCorrente contaCorrenteMaria = new ContaCorrente(conta2, usuario2);

        ContaInvestimento contaInvestimento1 = new ContaInvestimento(conta1, usuario1);
        ContaInvestimento contaInvestimento2 = new ContaInvestimento(conta2, usuario2);

        // Depositar
        contaCorrenteJoao.depositar(1000);
        contaCorrenteMaria.depositar(2000);

        contaInvestimento1.depositar(1000);
        contaInvestimento2.depositar(2000);

        // Sacar
        contaCorrenteJoao.sacar(500);
        contaCorrenteMaria.sacar(1000);

        contaInvestimento1.sacar(500);
        contaInvestimento2.sacar(1000);

        // Transferir
        contaCorrenteJoao.transferir(contaCorrenteMaria, 500);
        // contaInvestimento1.transferir(contaInvestimento2, 500); // POR ENQUANTO NÃO


        List<Transacao> teste = contaCorrenteMaria.getExtrato();
        for (Transacao transacao : teste) {
            System.out.println(transacao.getTransacao());
        }


    }
}
