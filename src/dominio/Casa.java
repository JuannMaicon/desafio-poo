package dominio;

public class Casa extends Imovel {

	// Construtor da classe Casa
	public Casa(int id, String endereco, int numero, Proprietario proprietario, int precoMensal) {

		// Chama o construtor da classe Imovel
		super(id, endereco, numero, proprietario, precoMensal);
	}

	// Indica se a casa esta alugada
	@Override
	public boolean estaAlugado() {
		return isAlugado();
	}

	// Retorna o status da casa
	@Override
	public String statusMensagem() {
		return isAlugado() ? "A casa está alugada." : "A casa está disponível.";
	}

}
