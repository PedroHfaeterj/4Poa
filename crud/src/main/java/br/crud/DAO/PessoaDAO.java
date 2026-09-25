package br.crud.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.crud.conexao.Conexao;
import br.crud.model.Pessoa;

public class PessoaDAO {
	
	public void criarTabela() {
		String sql = "CREATE TABLE IF NOT EXISTS pessoas ("
				   + "id INT AUTO_INCREMENT PRIMARY KEY, "
				   + "nome VARCHAR(255), "
				   + "idade INT, "
				   + "genero VARCHAR(50), "
				   + "cpf VARCHAR(14))";

		try (Connection conn = Conexao.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.execute();
			System.out.println("-> Tabela 'pessoas' verificada/criada com sucesso!\n");
			
		} catch (Exception e) {
			System.err.println("Erro ao criar tabela: " + e.getMessage());
		}
	}

	public void salvar(Pessoa pessoa) {
		String sql = "INSERT INTO pessoas (nome, idade, genero, cpf) VALUES (?, ?, ?, ?)";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, pessoa.getNome());
			stmt.setInt(2, pessoa.getIdade());
			stmt.setString(3, pessoa.getGenero());
			stmt.setString(4, pessoa.getCpf());
			stmt.executeUpdate();
			System.out.println("-> Pessoa '" + pessoa.getNome() + "' adicionada!");

		} catch (Exception e) {
			System.err.println("Erro ao salvar pessoa: " + e.getMessage());
		}
	}

	public List<Pessoa> listarTodas() {
		List<Pessoa> pessoas = new ArrayList<>();
		String sql = "SELECT * FROM pessoas";

		try (Connection conn = Conexao.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Pessoa p = new Pessoa(
						rs.getInt("id"), 
						rs.getString("nome"), 
						rs.getInt("idade"), 
						rs.getString("genero"), 
						rs.getString("cpf")
				);
				pessoas.add(p);
			}

		} catch (Exception e) {
			System.err.println("Erro ao listar pessoas: " + e.getMessage());
		}
		return pessoas;
	}

	public void atualizar(Pessoa pessoa) {
		String sql = "UPDATE pessoas SET nome = ?, idade = ?, genero = ?, cpf = ? WHERE id = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, pessoa.getNome());
			stmt.setInt(2, pessoa.getIdade());
			stmt.setString(3, pessoa.getGenero());
			stmt.setString(4, pessoa.getCpf());
			stmt.setInt(5, pessoa.getId());
			stmt.executeUpdate();
			System.out.println("-> Pessoa ID " + pessoa.getId() + " atualizada!");

		} catch (Exception e) {
			System.err.println("Erro ao atualizar pessoa: " + e.getMessage());
		}
	}

	public void deletar(int id) {
		String sql = "DELETE FROM pessoas WHERE id = ?";

		try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("-> Pessoa ID " + id + " removida!");

		} catch (Exception e) {
			System.err.println("Erro ao deletar pessoa: " + e.getMessage());
		}
	}
}