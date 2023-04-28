package userInterfaces;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import entidades.Cliente;
import entidades.ContaCorrente;

public class InterfaceDeUsuario {
	private Scanner leitor = new Scanner(System.in);
	private Cliente[] clientesDoBanco =  new Cliente[10];
	private ContaCorrente[] contasDoBanco = new ContaCorrente[10];
	private int countClientes = 0;
	private int countContas = 0;
	
	
	public void rodarMenuPrincipal() {
		int opcaoMenu=99;
		Cliente clienteLogado = null;
		boolean nextMenu = false;

		while(!nextMenu) {
			opcaoMenu = this.getAutenticacaoOpcao();
			switch (opcaoMenu) {
				case 1:
					this.clientesDoBanco[this.countClientes] = this.createCliente();
					this.countClientes++;
					
					if (this.countClientes == 10) {
						System.out.println("Numero maximo de clientes atingido!");
					}
					break;
				case 2:
					this.contasDoBanco[this.countContas] = this.createContaCorrente();
					this.countContas++;
					if (this.countContas == 10) {
						System.out.println("Numero maximo de contas atingido!");
					}
					break;
				case 3:
					System.out.println("Digite o nome do cliente para entrar: ");
					clienteLogado = this.getCliente();
					if (clienteLogado == null)
						System.out.println("Cliente nao encontrado");
					else if (clienteLogado.getConta() == null)
						System.out.println("Cliente não possui contaCorrente");
					else
						nextMenu = true;
					break;
				case 4:
					int runContas = 0;
					System.out.println("Listando contas existentes:");
					for (ContaCorrente conta: this.contasDoBanco) {
						if (conta == null)
							break;
						System.out.printf("A conta: %d\n",conta.getNumero());
						System.out.printf("Contem %.2f reais\n\n",conta.getSaldo());
						runContas++;
					}
					if (runContas == 0) {
						System.out.println("Nenhuma conta encontrada;");
					}
					break;
				case 99:
					System.out.println("Encerrando sessao...");
					nextMenu = true;
					
					break;
			}
		}
		
		while (opcaoMenu != 99) {
			opcaoMenu = getOperacoesOpcao();
			switch (opcaoMenu) {
				case 1:
					System.out.println("Digite o valor a ser depositado: ");
					float valorParaDepositar = leitor.nextFloat();
					clienteLogado.getConta().depositar(valorParaDepositar);
					System.out.println(clienteLogado.getConta().getSaldo());
					break;
				case 2:
					// sacar
					break;
				case 3:
					// consultar saldo
					break;
				case 99:
					// exit
					System.out.println("Encerrando sessao...");
					break;
			}
		}
	}
	
	private int getAutenticacaoOpcao() {
		System.out.println("------ Menu de Opcoes -------\n"
				+ "1 - Criar uma nova conta de cliente\n"
				+ "2 - Criar uma nova conta corrente\n"
				+ "3 - Entrar em uma conta corrente existente\n"
				+ "4 - Mostrar todas as costas assim como seu saldo e numero\n"
				+ "99 - Sair\n"
				+ "------ Digite a opcao desejada: ------");

		int opcao = this.leitor.nextInt();
		this.leitor.nextLine();

		return opcao;
	}

	private int getOperacoesOpcao() {
		System.out.println("------ Menu de Opcoes -------\n"
				+ "1 - Depositar\n"
				+ "2 - Sacar\n"
				+ "3 - Consultar saldo\n"
				+ "99 - Sair\n"
				+ "------ Digite a opcao desejada: ------");
		
		int opcao = this.leitor.nextInt();
		this.leitor.nextLine();

		return opcao;
	}

	private Cliente createCliente() {

		
		System.out.println("Digite o nome do cliente: ");
		String nomeCli = this.leitor.nextLine();
		
		System.out.println("Digite o seu cpf");
		String cpfCli = this.leitor.next();
		
		Cliente novoCli = new Cliente(nomeCli, cpfCli);

		System.out.println("Digite o seu endereco: ");
		novoCli.setEndereco(this.leitor.nextLine());

		System.out.printf("%s, sua conta foi criada com sucesso!\n", novoCli.getNome());
		System.out.println("Cria uma conta corrente para proseguir\n");

		return novoCli;
	}
	
	private ContaCorrente createContaCorrente() {
		System.out.print("Digite o nome do cliente titular: ");

		ContaCorrente novaConta;
		Random numeroRandomico;
		Cliente cliente = this.getCliente();

		if (cliente != null) {
			numeroRandomico = new Random();
			novaConta = new ContaCorrente();
			
			novaConta.setNumero(numeroRandomico.nextInt(99999999));

			cliente.setConta(novaConta);
			novaConta.setTitular(cliente);
						
			System.out.println("Digite o saldo Inicial: ");
			novaConta.depositar(this.leitor.nextFloat());
			
			System.out.printf("Conta %d criada com sucesso\n", novaConta.getNumero());
			return novaConta;
		}
		System.out.println("\nTitular nao encontrado. Crie um cliente antes.\n");
		return null;
	}
	
	private Cliente getCliente() {
		Cliente cliente = null;

		String nomeCliente = this.leitor.nextLine();

		for (int indice=0; indice < countClientes; indice++) {
			if (this.clientesDoBanco[indice].getNome().equals(nomeCliente)) {
				cliente = this.clientesDoBanco[indice];
				break;
			}
		}

		return cliente;
	}
}
