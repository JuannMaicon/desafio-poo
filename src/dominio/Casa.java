package dominio;

public class Casa extends Imovel {
	public Casa(int id, String endereco, int numero, Proprietario proprietario, int precoMensal) {
		super(id, endereco, numero, proprietario, precoMensal);
	}

	@Override
	public String estaAlugado() {
		return isAlugado() ? "A casa está alugada." : "A casa está disponível.";
	}

}
