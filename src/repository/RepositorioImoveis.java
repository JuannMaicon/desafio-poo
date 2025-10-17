package repository;

import java.util.*;

import dominio.Imovel;

public class RepositorioImoveis {
	
	//Estrutura de dados que armazena os imoveis pelo id
	private final Map<Integer, Imovel> mapa = new LinkedHashMap<>();
	
	//Atributo para sequencia de id
	private int sequencia = 1;

	//Gera e retorna o proximo id disponivel
	public int proximoId() {
		return sequencia++;
	}

	//Salva um novo imovel no repositorio
	public void salvar(Imovel imovel) {
		mapa.put(imovel.getId(), imovel);
	}

	//Busca um imovel pelo seu id
	public Imovel buscar(int id) {
		return mapa.get(id);
	}

	//Remove um imovel do repositorio com base no id
	public boolean deletar(int id) {
		return mapa.remove(id) != null;
	}
	
	//Retorna lista com todos os imoveis 
	public List<Imovel> listarTodos() {
		return new ArrayList<>(mapa.values());
	}

	//Retorna lista apenas com os imoveis que estao alugados
	public List<Imovel> listarAlugados() {
		List<Imovel> out = new ArrayList<>();
		for (Imovel i : mapa.values()) {
			if (i.estaAlugado())
				out.add(i);
		}

		return out;
	}

}
