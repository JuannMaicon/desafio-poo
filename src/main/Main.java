package main;

import interfaceUsuario.ImobiliariaUI;
import repository.RepositorioImoveis;
import service.ImobiliariaService;

public class Main {

	public static void main(String[] args) {

		// Instancia o repositório responsável por armazenar os imóveis
		RepositorioImoveis repo = new RepositorioImoveis();

		// Cria o serviço da imobiliária, que contém as regras de negócio
		ImobiliariaService service = new ImobiliariaService(repo);

		// Cria a interface do usuário e injeta a service nela
		ImobiliariaUI ui = new ImobiliariaUI(service);

		// Inicia o sistema e exibe o menu de opções
		ui.iniciar();
	}
}
