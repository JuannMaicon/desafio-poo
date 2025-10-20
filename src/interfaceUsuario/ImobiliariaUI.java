package interfaceUsuario;

import dominio.*;
import service.ImobiliariaService;
import java.util.List;
import java.util.Scanner;

public class ImobiliariaUI {

	// Serviço que contém as regras de negócio
	private final ImobiliariaService service;

	// Scanner
	private final Scanner sc = new Scanner(System.in);

	// Construtor que recebe a service
	public ImobiliariaUI(ImobiliariaService service) {
		this.service = service;
	}

	// Método principal da interface
	public void iniciar() {
		while (true) {
			exibirMenu();
			int opcao = lerInt("Escolha: "); // lê a opção escolhida

			// Estrutura de decisão para cada operação do sistema
			switch (opcao) {
			case 1 -> cadastrarCasa();
			case 2 -> cadastrarApartamento();
			case 3 -> listar(service.listarTodos(), "Imóveis cadastrados");
			case 4 -> listar(service.listarAlugados(), "Imóveis alugados");
			case 5 -> deletar();
			case 6 -> alugar();
			case 7 -> disponibilizar();
			case 8 -> calcular();
			case 0 -> {
				System.out.println("Até mais!");
				sc.close();
				return;
			}
			default -> System.out.println("Opção inválida.");
			}
		}
	}

	// Exibe o menu principal do sistema
	private void exibirMenu() {
		System.out.println("\n=== CORRETORA IMOBILIÁRIA ===");
		System.out.println("1. Cadastrar casa");
		System.out.println("2. Cadastrar apartamento");
		System.out.println("3. Listar todos");
		System.out.println("4. Listar alugados");
		System.out.println("5. Deletar imóvel");
		System.out.println("6. Alugar imóvel");
		System.out.println("7. Disponibilizar imóvel");
		System.out.println("8. Calcular aluguel (meses)");
		System.out.println("0. Sair");
	}

	// Cadastra uma nova casa no sistema
	private void cadastrarCasa() {
		System.out.println("\n-- Cadastro de CASA --");
		DadosImovel d = lerDadosImovel();
		Casa c = service.cadastrarCasa(d.endereco, d.numero, d.proprietario, d.preco);
		System.out.println("Casa cadastrada com sucesso!");
		imprimirResumo(c); // exibe o resumo do imóvel cadastrado
	}

	// Cadastra um novo apartamento
	private void cadastrarApartamento() {
		System.out.println("\n-- Cadastro de APARTAMENTO --");

		DadosImovel d = lerDadosImovel();

		// agora pergunta o número do apartamento
		System.out.print("Número do apartamento: ");
		int numeroApto = lerInt("");

		//salva
		Apartamento a = service.cadastrarApartamento(d.endereco, d.numero, numeroApto, d.proprietario, d.preco);

		// Mensagem de confirmação
		System.out.println("Apartamento cadastrado com sucesso!");

		// Exibe o resumo do imóvel cadastrado
		imprimirResumo(a);
	}

	// Lista imóveis de acordo com o tipo de listagem 
	private void listar(List<Imovel> lista, String titulo) {
		System.out.println("\n-- " + titulo + " --");
		if (lista.isEmpty()) {
			System.out.println("(Nenhum encontrado)");
			return;
		}
		lista.forEach(this::imprimirResumo);
	}

	// Deleta um imóvel a partir do ID
	private void deletar() {
		int id = lerInt("ID para deletar: ");
		System.out.println(service.deletarImovel(id) ? "Imóvel deletado!" : "ID não encontrado.");
	}

	// Aluga um imóvel alterando seu status interno
	private void alugar() {
		int id = lerInt("ID para alugar: ");
		System.out.println(
				service.alugarImovel(id) ? "Alugado com sucesso!" : "Falha ao alugar (já alugado ou inexistente).");
	}

	// Disponibiliza novamente um imóvel que estava alugado
	private void disponibilizar() {
		int id = lerInt("ID para disponibilizar: ");
		System.out.println(service.disponibilizarImovel(id) ? "Disponibilizado com sucesso!"
				: "Falha ao disponibilizar (já disponível ou inexistente).");
	}

	// Calcula o valor total do aluguel com base na quantidade de meses
	private void calcular() {
		int id = lerInt("ID do imóvel: ");
		int meses = lerInt("Meses: ");
		int total = service.calcularAluguel(id, meses);
		if (total < 0) {
			System.out.println("ID não encontrado.");
			return;
		}
		System.out.printf("Valor total do aluguel por %d mês(es): R$ %d%n", meses, total);
	}

	// Lê os dados comuns de um imóvel e do proprietário
	private DadosImovel lerDadosImovel() {
		System.out.print("Endereço: ");
		String endereco = sc.nextLine().trim();
		int numero = lerInt("Número: ");
		int preco = lerInt("Preço mensal (R$): ");

		System.out.println("-- Dados do proprietário --");
		System.out.print("Nome: ");
		String nome = sc.nextLine().trim();
		System.out.print("Telefone: ");
		String tel = sc.nextLine().trim();
		System.out.print("CPF: ");
		String cpf = sc.nextLine().trim();

		Proprietario p = new Proprietario(nome, tel, cpf);
		return new DadosImovel(endereco, numero, preco, p);
	}

	// Record auxiliar que agrupa as informações necessárias para criar um imóvel
	private record DadosImovel(String endereco, int numero, int preco, Proprietario proprietario) {
	}

	// Exibe um resumo formatado das informações de um imóvel
	private void imprimirResumo(Imovel i) {
		System.out.println(i.resumo());
		System.out.println("  Status: " + i.statusMensagem());
		System.out.println("  Contato proprietário: " + i.contatoProprietario());
	}

	// Faz a leitura de um número inteiro com tratamento de erro
	private int lerInt(String msg) {
		while (true) {
			try {
				if (!msg.isEmpty())
					System.out.print(msg);
				return Integer.parseInt(sc.nextLine().trim());
			} catch (Exception e) {
				System.out.println("Digite um número válido.");
			}
		}
	}
}
