package br.crud.main;

import br.crud.DAO.PessoaDAO;
import br.crud.model.Pessoa;

public class Main {
	public static void main(String[] args) {
		PessoaDAO dao = new PessoaDAO();
		dao.criarTabela();

		System.out.println("=== 1. TESTANDO O 'CREATE' ===");
		dao.salvar(new Pessoa("João Silva", 25, "Masculino", "111.111.111-11"));
		dao.salvar(new Pessoa("Maria Oliveira", 30, "Feminino", "222.222.222-22"));
		dao.salvar(new Pessoa("Carlos Souza", 45, "Masculino", "333.333.333-33"));

		System.out.println("\n=== 2. TESTANDO O 'READ' ===");
		
		dao.listarTodas().forEach(System.out::println);

		System.out.println("\n=== 3. TESTANDO O 'UPDATE' ===");

		Pessoa pessoaAtualizada = new Pessoa(1, "João Silva Atualizado", 26, "Masculino", "111.111.111-11");
		dao.atualizar(pessoaAtualizada);
		
	
		dao.listarTodas().forEach(System.out::println);

		System.out.println("\n=== 4. TESTANDO O 'DELETE' ===");
		dao.deletar(3);
		
		System.out.println("\n=== RESULTADO FINAL NO BANCO ===");
		
		dao.listarTodas().forEach(System.out::println);
	}
}