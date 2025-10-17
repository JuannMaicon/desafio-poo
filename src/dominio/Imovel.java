package dominio;

public abstract class Imovel {
    // Encapsulamento exigido: protected
    protected String endereco;
    protected int numero;
    protected boolean alugado;

    // Composição: um imóvel "tem um" proprietário
    protected Proprietario proprietario;

    // Atributos de suporte (id e preço)
    private final int id;
    private int precoMensal;

    protected Imovel(int id, String endereco, int numero, Proprietario proprietario, int precoMensal) {
        this.id = id;
        this.endereco = endereco;
        this.numero = numero;
        this.proprietario = proprietario;
        this.precoMensal = Math.max(0, precoMensal);
        this.alugado = false;
    }

    // ===== Getters/Setters controlados (encapsulamento) =====
    public int getId() { return id; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) {
        if (endereco != null && !endereco.isBlank()) this.endereco = endereco.trim();
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) {
        if (numero > 0) this.numero = numero;
    }

    // boolean para lógica
    public boolean isAlugado() { return alugado; }
    // controle interno do estado (mantém encapsulamento)
    public void setAlugadoInterno(boolean alugado) { this.alugado = alugado; }

    public Proprietario getProprietario() { return proprietario; }
    public void setProprietario(Proprietario proprietario) {
        if (proprietario != null) this.proprietario = proprietario;
    }

    public int getPrecoMensal() { return precoMensal; }
    public void setPrecoMensal(int precoMensal) {
        if (precoMensal >= 0) this.precoMensal = precoMensal;
    }

    // ===== Métodos do enunciado =====

    // contato do proprietário (sem acento para evitar problemas)
    public String contatoProprietario() {
        return proprietario == null ? "Sem proprietário" : proprietario.contato();
    }

    // período em MESES (com descontos opcionais)
    public int calcularAluguel(int meses) {
        if (meses <= 0) return 0;
        int bruto = getPrecoMensal() * meses;

        double desconto = 0.0;
        if (meses >= 36) desconto = 0.15;       // 15% (3 anos)
        else if (meses >= 24) desconto = 0.10;  // 10% (2 anos)
        else if (meses >= 12) desconto = 0.05;  // 5%  (1 ano)

        return (int) Math.round(bruto * (1.0 - desconto));
    }

    // ===== Polimorfismo exigido =====
    // Deve retornar a MENSAGEM de status (String) e será sobrescrito em Casa/Apartamento
    public abstract String estaAlugado();

    // Utilitário para listagem
    public String resumo() {
        return String.format(
            "#%d | %s, %d | %s | Aluguel: R$ %d | Proprietário: %s",
            id, endereco, numero, (alugado ? "ALUGADO" : "DISPONÍVEL"),
            precoMensal,
            (proprietario != null ? proprietario.getNome() : "—")
        );
    }
}
