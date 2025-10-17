package dominio;

public class Proprietario {
	private String nome;
	private String telefone;
	private String cpf;

	//Construtor e inicialização.
	public Proprietario(String nome, String telefone, String cpf) {
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
	}

	
	//get, set
	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}

	//Metodo para retornar contato
	public String contato() {
		return String.format("%s (Tel: %s, CPF: %s)", nome, telefone, cpf);
	}
}
