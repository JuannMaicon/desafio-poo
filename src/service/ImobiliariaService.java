package service;

import repository.RepositorioImoveis;

import java.util.List;

import dominio.*;

public class ImobiliariaService {
	private final RepositorioImoveis repo;

	public ImobiliariaService(RepositorioImoveis repo) {
		this.repo = repo;
	}

	public Casa cadastrarCasa(String endereco, int numero, Proprietario p, int preco) {
		Casa c = new Casa(repo.proximoId(), endereco, numero, p, preco);
		repo.salvar(c);
		return c;
	}

	public Apartamento cadastrarApartamento(String endereco, int numero, Proprietario p, int preco) {
		Apartamento a = new Apartamento(repo.proximoId(), endereco, numero, p, preco);
		repo.salvar(a);
		return a;
	}

	public boolean deletarImovel(int id) {
		return repo.deletar(id);
	}

	public boolean alugarImovel(int id) {
		Imovel i = repo.buscar(id);
		if (i == null || i.isAlugado())
			return false; // usa boolean
		setAlugado(i, true);
		return true;
	}

	public boolean disponibilizarImovel(int id) {
		Imovel i = repo.buscar(id);
		if (i == null || !i.isAlugado())
			return false; // usa boolean
		setAlugado(i, false);
		return true;
	}

	public int calcularAluguel(int id, int meses) {
		Imovel i = repo.buscar(id);
		if (i == null)
			return -1;
		return i.calcularAluguel(meses);
	}

	public Imovel buscar(int id) {
		return repo.buscar(id);
	}

	public List<Imovel> listarTodos() {
		return repo.listarTodos();
	}

	public List<Imovel> listarAlugados() {
		return repo.listarAlugados();
	}

	// Ponte controlada para acessar o método protected setAlugadoInterno
	private void setAlugado(Imovel i, boolean valor) {
		class Bridge extends Imovel {
			private Bridge() {
				super(-1, "", 1, null, 0);
			}

			@Override
			public String estaAlugado() {
				return "";
			} // atende ao método abstrato

			private void apply(Imovel alvo, boolean val) {
				alvo.setAlugadoInterno(val);
			}
		}
		new Bridge().apply(i, valor);
	}
}
