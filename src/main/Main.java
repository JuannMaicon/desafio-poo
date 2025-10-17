package main;

import repository.RepositorioImoveis;
import service.ImobiliariaService;

import java.util.List;
import java.util.Scanner;

import dominio.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ImobiliariaService service = new ImobiliariaService(new RepositorioImoveis());

        while (true) {
            System.out.println("\n=== CORRETORA IMOBILIÁRIA ===");
            System.out.println("1) Cadastrar CASA");
            System.out.println("2) Cadastrar APARTAMENTO");
            System.out.println("3) Listar todos");
            System.out.println("4) Listar ALUGADOS");
            System.out.println("5) Deletar imóvel");
            System.out.println("6) Alugar imóvel");
            System.out.println("7) Disponibilizar (desalugar)");
            System.out.println("8) Calcular aluguel (meses)");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");

            int op = lerInt(sc);

            switch (op) {
                case 1 -> cadastrarCasa(sc, service);
                case 2 -> cadastrarApto(sc, service);
                case 3 -> listarTodos(service);
                case 4 -> listarAlugados(service);
                case 5 -> deletar(sc, service);
                case 6 -> alugar(sc, service);
                case 7 -> disponibilizar(sc, service);
                case 8 -> calcular(sc, service);
                case 0 -> { System.out.println("Até mais!"); return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarCasa(Scanner sc, ImobiliariaService service) {
        System.out.println("\n-- Cadastro de CASA --");
        DadosImovel d = lerDadosImovel(sc);
        Casa c = service.cadastrarCasa(d.endereco, d.numero, d.proprietario, d.preco);
        System.out.println("Casa cadastrada!");
        imprimirResumo(c);
    }

    private static void cadastrarApto(Scanner sc, ImobiliariaService service) {
        System.out.println("\n-- Cadastro de APARTAMENTO --");
        DadosImovel d = lerDadosImovel(sc);
        Apartamento a = service.cadastrarApartamento(d.endereco, d.numero, d.proprietario, d.preco);
        System.out.println("Apartamento cadastrado!");
        imprimirResumo(a);
    }

    private static void listarTodos(ImobiliariaService service) {
        System.out.println("\n-- Lista de Imóveis --");
        List<Imovel> lista = service.listarTodos();
        if (lista.isEmpty()) { System.out.println("(vazio)"); return; }
        for (Imovel i : lista) {
            imprimirResumo(i);
        }
    }

    private static void listarAlugados(ImobiliariaService service) {
        System.out.println("\n-- Alugados --");
        List<Imovel> lista = service.listarAlugados();
        if (lista.isEmpty()) { System.out.println("(nenhum alugado)"); return; }
        for (Imovel i : lista) {
            imprimirResumo(i);
        }
    }

    private static void deletar(Scanner sc, ImobiliariaService service) {
        System.out.print("\nID do imóvel para deletar: ");
        int id = lerInt(sc);
        boolean ok = service.deletarImovel(id);
        System.out.println(ok ? "Imóvel deletado." : "ID não encontrado.");
    }

    private static void alugar(Scanner sc, ImobiliariaService service) {
        System.out.print("\nID do imóvel para alugar: ");
        int id = lerInt(sc);
        boolean ok = service.alugarImovel(id);
        Imovel i = service.buscar(id);
        if (i == null) { System.out.println("ID não encontrado."); return; }
        System.out.println(ok ? "Alugado com sucesso!" : "Não foi possível alugar (já alugado?).");
        System.out.println(i.estaAlugado()); // mensagem
    }

    private static void disponibilizar(Scanner sc, ImobiliariaService service) {
        System.out.print("\nID do imóvel para disponibilizar: ");
        int id = lerInt(sc);
        boolean ok = service.disponibilizarImovel(id);
        Imovel i = service.buscar(id);
        if (i == null) { System.out.println("ID não encontrado."); return; }
        System.out.println(ok ? "Imóvel disponibilizado." : "Não foi possível disponibilizar (já disponível?).");
        System.out.println(i.estaAlugado()); // mensagem
    }

    private static void calcular(Scanner sc, ImobiliariaService service) {
        System.out.print("\nID do imóvel: ");
        int id = lerInt(sc);
        Imovel i = service.buscar(id);
        if (i == null) { System.out.println("ID não encontrado."); return; }
        System.out.print("Período em meses: ");
        int meses = lerInt(sc);
        int total = service.calcularAluguel(id, meses);
        System.out.printf("Total do aluguel por %d mês(es): R$ %d%n", meses, total);
        System.out.println(i.estaAlugado()); // mensagem
        System.out.println("Contato do proprietário: " + i.contatoProprietario()); // sem acento
    }

    // ===== utilitários =====

    private record DadosImovel(String endereco, int numero, int preco, Proprietario proprietario) {}

    private static DadosImovel lerDadosImovel(Scanner sc) {
        System.out.print("Endereço (rua/avenida): ");
        String endereco = sc.nextLine().trim();

        System.out.print("Número: ");
        int numero = lerInt(sc);

        System.out.print("Preço mensal (R$): ");
        int preco = lerInt(sc);

        System.out.println("\n-- Proprietário --");
        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        System.out.print("Telefone: ");
        String tel = sc.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = sc.nextLine().trim();

        Proprietario p = new Proprietario(nome, tel, cpf);
        return new DadosImovel(endereco, numero, preco, p);
    }

    private static void imprimirResumo(Imovel i) {
        System.out.println(i.resumo());
        System.out.println("  Status: " + i.estaAlugado()); // mensagem
        System.out.println("  Contato proprietário: " + i.contatoProprietario()); // sem acento
    }

    private static int lerInt(Scanner sc) {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Digite um inteiro válido: ");
            }
        }
    }
}
