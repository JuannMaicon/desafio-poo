package dominio;

public class Apartamento extends Imovel {
    public Apartamento(int id, String endereco, int numero, Proprietario proprietario, int precoMensal) {
        super(id, endereco, numero, proprietario, precoMensal);
    }

    @Override
    public String estaAlugado() {
        return isAlugado()
            ? String.format("O apartamento de número %d está alugado.", getNumero())
            : String.format("O apartamento de número %d está disponível.", getNumero());
    }

}
