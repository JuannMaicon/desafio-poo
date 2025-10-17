package main;

import repository.RepositorioImoveis;
import service.ImobiliariaService;

import java.util.List;
import java.util.Scanner;

import dominio.*;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Instancia o serviço principal da imobiliária
		// responsável por gerenciar os imóveis através do repositório
		ImobiliariaService service = new ImobiliariaService(new RepositorioImoveis());

		// Menu de serviços
		while (true) {
			System.out.println("\n CORRETORA IMOBILIÁRIA ");
			System.out.println("DIGITE A OPÇÃO DESEJADA");
			System.out.println("1 Cadastrar casa");
			System.out.println("2 Cadastrar apartamento");
			System.out.println("3 Listar todos");
			System.out.println("4 Listar ALUGADOS");
			System.out.println("5 Deletar imóvel");
			System.out.println("6 Alugar imóvel");
			System.out.println("7 Disponibilizar");
			System.out.println("8 Calcular aluguel (meses)");
			System.out.println("0 Sair");
			System.out.print("Escolha: ");

			int opcao = lerInt(scan);

			// Estrutura de decisão para cada operação
			switch (opcao) {
			case 1 -> cadastrarCasa(scan, service);
			case 2 -> cadastrarApto(scan, service);
			case 3 -> listarTodos(service);
			case 4 -> listarAlugados(service);
			case 5 -> deletar(scan, service);
			case 6 -> alugar(scan, service);
			case 7 -> disponibilizar(scan, service);
			case 8 -> calcular(scan, service);
			case 0 -> {
				System.out.println("Até mais!");
				return;
			}
			default -> System.out.println("Opção inválida.");
			}
		}
	}

	// Cadastra uma nova casa no sistema.
	private static void cadastrarCasa(Scanner sc, ImobiliariaService service) {
		System.out.println("\n-- Cadastro de CASA --");
		DadosImovel d = lerDadosImovel(sc);

		// Cria e salva a nova casa
		Casa c = service.cadastrarCasa(d.endereco, d.numero, d.proprietario, d.preco);
		System.out.println("Casa cadastrada!");
		imprimirResumo(c);
	}

	// Cadastra um novo apartamento
	private static void cadastrarApto(Scanner sc, ImobiliariaService service) {
		System.out.println("\n-- Cadastro de APARTAMENTO --");
		System.out.print("Endereço (rua/avenida): ");
		String endereco = sc.nextLine().trim();

		// Leitura de dados do apt.
		System.out.print("Número do prédio: ");
		int numero = lerInt(sc);

		System.out.print("Número do apartamento: ");
		int numeroApto = lerInt(sc);

		System.out.print("Preço mensal (R$): ");
		int preco = lerInt(sc);

		// Leitura de dados do proprietario
		System.out.println("\n-- Proprietário --");
		System.out.print("Nome: ");
		String nome = sc.nextLine().trim();
		System.out.print("Telefone: ");
		String tel = sc.nextLine().trim();
		System.out.print("CPF: ");
		String cpf = sc.nextLine().trim();

		// Instancia do proprietario
		Proprietario p = new Proprietario(nome, tel, cpf);

		// Criação e salvamento do apartamento
		Apartamento a = service.cadastrarApartamento(endereco, numero, numeroApto, p, preco);
		System.out.println("Apartamento cadastrado!");
		imprimirResumo(a);
	}

	//Exibe a lista de imoveis cadastrados
	private static void listarTodos(ImobiliariaService service) {
		System.out.println("\n-- Lista de Imóveis --");
		List<Imovel> lista = service.listarTodos();
		if (lista.isEmpty()) {
			System.out.println("(vazio)");
			return;
		}
		
		// Percorre e imprime cada imovel cadastrado
		for (Imovel i : lista) {
			imprimirResumo(i);
		}
	}

    //Exibe a lista de imoveis alugados
	private static void listarAlugados(ImobiliariaService service) {
		System.out.println("\n** Alugados **");
		List<Imovel> lista = service.listarAlugados();
		if (lista.isEmpty()) {
			System.out.println("(nenhum alugado)");
			return;
		}
		
		// Percorre e imprime cada imovel alugado
		for (Imovel i : lista) {
			imprimirResumo(i);
		}
	}

	//Deleta imovel pelo id
	private static void deletar(Scanner sc, ImobiliariaService service) {
		System.out.print("\nID do imóvel para deletar: ");
		int id = lerInt(sc);
		boolean ok = service.deletarImovel(id);
		System.out.println(ok ? "Imóvel deletado." : "ID não encontrado.");
	}

	//Aluga um imóvel alterando seu status interno
	private static void alugar(Scanner sc, ImobiliariaService service) {
		System.out.print("\nID do imóvel para alugar: ");
		int id = lerInt(sc);
		boolean ok = service.alugarImovel(id);
		Imovel i = service.buscar(id);
		if (i == null) {
			System.out.println("ID não encontrado.");
			return;
		}
		System.out.println(ok ? "Alugado com sucesso!" : "Não foi possível alugar (já alugado?).");
		System.out.println(i.statusMensagem()); // ← exibe mensagem
	}

	//Disponibiliza um imóvel
	private static void disponibilizar(Scanner sc, ImobiliariaService service) {
		System.out.print("\nID do imóvel para disponibilizar: ");
		int id = lerInt(sc);
		boolean ok = service.disponibilizarImovel(id);
		Imovel i = service.buscar(id);
		if (i == null) {
			System.out.println("ID não encontrado.");
			return;
		}
		System.out.println(ok ? "Imóvel disponibilizado." : "Não foi possível disponibilizar (já disponível?).");
		System.out.println(i.statusMensagem());
	}

	//Calcula o valor total de aluguel por um período em meses
	private static void calcular(Scanner sc, ImobiliariaService service) {
		System.out.print("\nID do imóvel: ");
		int id = lerInt(sc);
		Imovel i = service.buscar(id);
		if (i == null) {
			System.out.println("ID não encontrado.");
			return;
		}
		System.out.print("Período em meses: ");
		int meses = lerInt(sc);
		int total = service.calcularAluguel(id, meses);
		System.out.printf("Total do aluguel por %d mês(es): R$ %d%n", meses, total);
		System.out.println(i.statusMensagem()); // ← exibe mensagem
		System.out.println("Contato do proprietário: " + i.contatoProprietario());
	}

	//Record auxiliar para agrupar os dados do imóvel durante o cadastro
	private record DadosImovel(String endereco, int numero, int preco, Proprietario proprietario) {
	}

	//Coleta os dados comuns de um imóvel e do proprietário
	private static DadosImovel lerDadosImovel(Scanner scan) {
		System.out.print("Endereço (rua/avenida): ");
		String endereco = scan.nextLine().trim();

		System.out.print("Número: ");
		int numero = lerInt(scan);

		System.out.print("Preço mensal (R$): ");
		int preco = lerInt(scan);

		System.out.println("\n-- Proprietário --");
		System.out.print("Nome: ");
		String nome = scan.nextLine().trim();
		System.out.print("Telefone: ");
		String tel = scan.nextLine().trim();
		System.out.print("CPF: ");
		String cpf = scan.nextLine().trim();

		Proprietario p = new Proprietario(nome, tel, cpf);
		return new DadosImovel(endereco, numero, preco, p);
	}

	//Exibe as informações detalhadas de um imovel
	private static void imprimirResumo(Imovel i) {
		System.out.println(i.resumo());
		System.out.println("  Status: " + i.statusMensagem());
		System.out.println("  Contato proprietário: " + i.contatoProprietario());
	}

	//Faz a leitura de um nmero inteiro de forma segura,
    //tratamento de exceções de entrada inválida
	private static int lerInt(Scanner sc) {
		while (true) {
			try {
				String s = sc.nextLine().trim();
				return Integer.parseInt(s);
			} catch (Exception e) {
				System.out.print("Digite um inteiro válido: ");
			}
		}
	}
}
