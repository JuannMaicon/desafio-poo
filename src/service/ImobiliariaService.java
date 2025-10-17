package service;

import repository.RepositorioImoveis;
import java.util.List;
import dominio.*;

//Responsável por aplicar as regras de negocio
public class ImobiliariaService {

	// Repositorio responsavel por armazenar e recuperar os imoveis
	private final RepositorioImoveis repo;

	// Construtor
	public ImobiliariaService(RepositorioImoveis repo) {
		this.repo = repo;
	}

	// Cadastra casa e salva no repositorio
	public Casa cadastrarCasa(String endereco, int numero, Proprietario p, int preco) {
		Casa c = new Casa(repo.proximoId(), endereco, numero, p, preco);
		repo.salvar(c);
		return c;
	}

	// Cadastra apt e salva no repositorio
	public Apartamento cadastrarApartamento(String endereco, int numero, int numeroApartamento, Proprietario p,
			int preco) {
		Apartamento a = new Apartamento(repo.proximoId(), endereco, numero, numeroApartamento, p, preco);
		repo.salvar(a);
		return a;
	}

	// Exclui imovel do repositrio com base no id
	public boolean deletarImovel(int id) {
		return repo.deletar(id);
	}

	// Aluga um imovel caso ele exista e esteja disponível
	public boolean alugarImovel(int id) {
		Imovel i = repo.buscar(id);
		if (i == null || i.isAlugado())
			return false;
		i.setAlugadoInterno(true); // ✅ agora é público
		return true;
	}

	// Disponibiliza novamente um imóvel já alugado
	public boolean disponibilizarImovel(int id) {
		Imovel i = repo.buscar(id);
		if (i == null || !i.isAlugado())
			return false;
		i.setAlugadoInterno(false); // ✅ agora é público
		return true;
	}

	// Calcula o valor total do aluguel de um imóvel
	// Com base na quantidade de meses
	public int calcularAluguel(int id, int meses) {
		Imovel i = repo.buscar(id);
		if (i == null)
			return -1;
		return i.calcularAluguel(meses);
	}

	// Busca um imóvel pelo id
	public Imovel buscar(int id) {
		return repo.buscar(id);
	}

	// Lista imoveis
	public List<Imovel> listarTodos() {
		return repo.listarTodos();
	}

	// Lista imoveis alugados
	public List<Imovel> listarAlugados() {
		return repo.listarAlugados();
	}
}
