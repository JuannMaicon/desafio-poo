package dominio;

public class Apartamento extends Imovel {

	// Atributo necessario para salvar numero do apt
	private int numeroApartamento;

	// Metodo construtor
	public Apartamento(int id, String endereco, int numero, int numeroApartamento, Proprietario proprietario,
			int precoMensal) {
		super(id, endereco, numero, proprietario, precoMensal);

		// Inicialização do número do apartamento
		this.numeroApartamento = numeroApartamento;
	}

	// Metodos acessores get e set
	public int getNumeroApartamento() {
		return numeroApartamento;
	}

	public void setNumeroApartamento(int numeroApartamento) {
		if (numeroApartamento > 0)

			this.numeroApartamento = numeroApartamento;
	}

	// Metodos sobrescritos polimorfismo

	// Verifica se o apartamento está alugado
	@Override
	public boolean estaAlugado() {
		return isAlugado();
	}

	// Retorna uma mensagem indicando o status do apt
	@Override
	public String statusMensagem() {
		return isAlugado() ? "O apartamento de número " + getNumeroApartamento() + " está alugado."
				: "O apartamento de número " + getNumeroApartamento() + " está disponível.";
	}

	// Gera um resumo com as principais informações do apt
	@Override
	public String resumo() {
		return String.format("#%d | %s, %d (Ap %d) | %s | Aluguel: R$ %d | Proprietário: %s", getId(), getEndereco(),
				getNumero(), numeroApartamento, (isAlugado() ? "ALUGADO" : "DISPONÍVEL"), getPrecoMensal(),
				(getProprietario() != null ? getProprietario().getNome() : "—"));
	}
}
