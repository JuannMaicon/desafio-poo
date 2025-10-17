package dominio;

public abstract class Imovel {

	// Atributos principais
	protected String endereco;
	protected int numero;
	protected boolean alugado;
	protected Proprietario proprietario;

	// Atributos de controle e valor
	private final int id;
	private int precoMensal;

	// Construtor da classe imovel
	protected Imovel(int id, String endereco, int numero, Proprietario proprietario, int precoMensal) {
		this.id = id;
		this.endereco = endereco;
		this.numero = numero;
		this.proprietario = proprietario;
		this.precoMensal = Math.max(0, precoMensal);
		this.alugado = false;
	}

	// Metodos get e set
	public int getId() {
		return id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if (endereco != null && !endereco.isBlank())
			this.endereco = endereco.trim();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		if (numero > 0)
			this.numero = numero;
	}

	public boolean isAlugado() {
		return alugado;
	}

	public void setAlugadoInterno(boolean alugado) {
		this.alugado = alugado;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		if (proprietario != null)
			this.proprietario = proprietario;
	}

	public int getPrecoMensal() {
		return precoMensal;
	}

	public void setPrecoMensal(int precoMensal) {
		if (precoMensal >= 0)
			this.precoMensal = precoMensal;
	}

	// Retorna o contato do proprietario ou msg padrao
	public String contatoProprietario() {
		return proprietario == null ? "Sem proprietário" : proprietario.contato();
	}

	// Calcula o valor total do aluguel com desconto conforme o tempo
	public int calcularAluguel(int meses) {
		if (meses <= 0)
			return 0;
		int bruto = getPrecoMensal() * meses;
		double desconto = 0.0;
		if (meses >= 36)
			desconto = 0.15;
		else if (meses >= 24)
			desconto = 0.10;
		else if (meses >= 12)
			desconto = 0.05;
		return (int) Math.round(bruto * (1.0 - desconto));
	}

	// Metodos abstratos para as subclasses
	public abstract boolean estaAlugado();

	public abstract String statusMensagem();

	// Retorna um resumo com as principais informações do imovel
	public String resumo() {
		return String.format("#%d | %s, %d | %s | Aluguel: R$ %d | Proprietário: %s", id, endereco, numero,
				(alugado ? "ALUGADO" : "DISPONÍVEL"), precoMensal,
				(proprietario != null ? proprietario.getNome() : "—"));
	}
}
