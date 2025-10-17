package repository;

import java.util.*;

import dominio.Imovel;

public class RepositorioImoveis {
    private final Map<Integer, Imovel> mapa = new LinkedHashMap<>();
    private int sequence = 1;

    public int proximoId() { return sequence++; }

    public void salvar(Imovel imovel) { mapa.put(imovel.getId(), imovel); }

    public Imovel buscar(int id) { return mapa.get(id); }

    public boolean deletar(int id) { return mapa.remove(id) != null; }

    public List<Imovel> listarTodos() { return new ArrayList<>(mapa.values()); }

    public List<Imovel> listarAlugados() {
        List<Imovel> out = new ArrayList<>();
        for (Imovel i : mapa.values()) {
            if (i.isAlugado()) out.add(i); // ← troquei estaAlugado() por isAlugado()
        }
        return out;
    }

}
