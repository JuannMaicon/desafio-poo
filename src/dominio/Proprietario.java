package dominio;

public class Proprietario {
	private String nome;
	private String telefone;
	private String cpf;

	public Proprietario(String nome, String telefone, String cpf) {
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public String contato() {
		return String.format("%s (Tel: %s, CPF: %s)", nome, telefone, cpf);
	}
}
